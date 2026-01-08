package com.bajaj.sim.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private Long orderId;
    private String userId;
    private String symbol;
    private Side side;
    private Style style;
    private int quantity;
    private BigDecimal price;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order() {}

    public Order(Long orderId, String userId, String symbol, Side side, Style style, int quantity, BigDecimal price) {
        this.orderId = orderId;
        this.userId = userId;
        this.symbol = symbol;
        this.side = side;
        this.style = style;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.NEW;
        this.createdAt = LocalDateTime.now();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}