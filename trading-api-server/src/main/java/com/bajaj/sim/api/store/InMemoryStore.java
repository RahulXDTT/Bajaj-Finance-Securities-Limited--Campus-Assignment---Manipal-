package com.bajaj.sim.api.store;

import com.bajaj.sim.api.model.Instrument;
import com.bajaj.sim.api.model.Order;
import com.bajaj.sim.api.model.Trade;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryStore {
    private final Map<String, Instrument> instruments = new ConcurrentHashMap<>();
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final List<Trade> trades = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong orderIdGenerator = new AtomicLong(1);
    private final AtomicLong tradeIdGenerator = new AtomicLong(1);

    public void addInstrument(Instrument instrument) {
        instruments.put(instrument.getSymbol(), instrument);
    }

    public Instrument getInstrument(String symbol) {
        return instruments.get(symbol);
    }

    public Collection<Instrument> getAllInstruments() {
        return instruments.values();
    }

    public void updateLtp(String symbol, java.math.BigDecimal ltp) {
        Instrument instrument = instruments.get(symbol);
        if (instrument != null) {
            instrument.setLastTradedPrice(ltp);
        }
    }

    public Long saveOrder(Order order) {
        Long id = orderIdGenerator.getAndIncrement();
        order.setOrderId(id);
        orders.put(id, order);
        return id;
    }

    public Order getOrder(Long orderId) {
        return orders.get(orderId);
    }

    public void updateOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Long saveTrade(Trade trade) {
        Long id = tradeIdGenerator.getAndIncrement();
        trade.setTradeId(id);
        trades.add(trade);
        return id;
    }

    public List<Trade> getAllTrades() {
        return new ArrayList<>(trades);
    }
}