package com.bajaj.sim.api.service;

import com.bajaj.sim.api.model.*;
import com.bajaj.sim.api.store.InMemoryStore;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TradingService {
    private final InMemoryStore store;

    public TradingService(InMemoryStore store) {
        this.store = store;
    }

    public List<Instrument> listInstruments() {
        return new ArrayList<>(store.getAllInstruments());
    }

    public PlaceOrderResponse placeOrder(PlaceOrderRequest req) {
        Instrument inst = store.getInstrument(req.getSymbol());
        if (inst == null) {
            throw new IllegalArgumentException("Instrument not found: " + req.getSymbol());
        }
        if (req.getStyle() == Style.LIMIT && req.getPrice() == null) {
            throw new IllegalArgumentException("Price required for LIMIT orders");
        }
        Order order = new Order(null, "user1", req.getSymbol(), req.getSide(), req.getStyle(), req.getQuantity(), req.getPrice());
        Long orderId = store.saveOrder(order);
        order.setStatus(OrderStatus.PLACED);
        store.updateOrder(order);
        boolean executed = false;
        BigDecimal execPrice = inst.getLastTradedPrice();
        if (req.getStyle() == Style.MARKET) {
            executed = true;
        } else if (req.getStyle() == Style.LIMIT) {
            if ((req.getSide() == Side.BUY && req.getPrice().compareTo(inst.getLastTradedPrice()) >= 0) ||
                (req.getSide() == Side.SELL && req.getPrice().compareTo(inst.getLastTradedPrice()) <= 0)) {
                executed = true;
                execPrice = req.getPrice();
            }
        }
        if (executed) {
            order.setStatus(OrderStatus.EXECUTED);
            store.updateOrder(order);
            Trade trade = new Trade(null, orderId, req.getSymbol(), req.getSide(), req.getQuantity(), execPrice);
            store.saveTrade(trade);
        }
        return new PlaceOrderResponse(orderId, order.getStatus(), executed ? "Order executed" : "Order placed");
    }

    public Order getOrder(Long orderId) {
        Order order = store.getOrder(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }
        return order;
    }

    public List<Trade> listTrades() {
        return store.getAllTrades();
    }

    public List<Holding> getPortfolio() {
        Map<String, Holding> holdings = new HashMap<>();
        for (Trade trade : store.getAllTrades()) {
            Holding h = holdings.computeIfAbsent(trade.getSymbol(), k -> new Holding(k, 0, BigDecimal.ZERO, BigDecimal.ZERO));
            if (trade.getSide() == Side.BUY) {
                BigDecimal totalCost = h.getAveragePrice().multiply(BigDecimal.valueOf(h.getQuantity()));
                totalCost = totalCost.add(trade.getPrice().multiply(BigDecimal.valueOf(trade.getQuantity())));
                h.setQuantity(h.getQuantity() + trade.getQuantity());
                h.setAveragePrice(totalCost.divide(BigDecimal.valueOf(h.getQuantity()), 2, RoundingMode.HALF_UP));
            } else {
                h.setQuantity(h.getQuantity() - trade.getQuantity());
            }
        }
        for (Holding h : holdings.values()) {
            if (h.getQuantity() > 0) {
                Instrument inst = store.getInstrument(h.getSymbol());
                if (inst != null) {
                    h.setCurrentValue(inst.getLastTradedPrice().multiply(BigDecimal.valueOf(h.getQuantity())));
                }
            }
        }
        return holdings.values().stream().filter(h -> h.getQuantity() > 0).collect(Collectors.toList());
    }

    public void updateLtp(UpdateLtpRequest req) {
        store.updateLtp(req.getSymbol(), req.getLastTradedPrice());
    }
}