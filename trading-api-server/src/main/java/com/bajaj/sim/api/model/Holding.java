package com.bajaj.sim.api.model;

import java.math.BigDecimal;

public class Holding {
    private String symbol;
    private int quantity;
    private BigDecimal averagePrice;
    private BigDecimal currentValue;

    public Holding() {}

    public Holding(String symbol, int quantity, BigDecimal averagePrice, BigDecimal currentValue) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
        this.currentValue = currentValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }
}