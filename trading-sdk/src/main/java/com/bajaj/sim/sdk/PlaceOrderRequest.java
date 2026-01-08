package com.bajaj.sim.sdk;

import java.math.BigDecimal;

public class PlaceOrderRequest {
    private String symbol;
    private Side side;
    private Style style;
    private int quantity;
    private BigDecimal price;

    public PlaceOrderRequest() {}

    public PlaceOrderRequest(String symbol, Side side, Style style, int quantity, BigDecimal price) {
        this.symbol = symbol;
        this.side = side;
        this.style = style;
        this.quantity = quantity;
        this.price = price;
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
}