package com.bajaj.sim.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Trade {
    private Long tradeId;
    private Long orderId;
    private String symbol;
    private Side side;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime executedAt;

    public Trade() {}

    public Trade(Long tradeId, Long orderId, String symbol, Side side, int quantity, BigDecimal price) {
        this.tradeId = tradeId;
        this.orderId = orderId;
        this.symbol = symbol;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
        this.executedAt = LocalDateTime.now();
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }
}