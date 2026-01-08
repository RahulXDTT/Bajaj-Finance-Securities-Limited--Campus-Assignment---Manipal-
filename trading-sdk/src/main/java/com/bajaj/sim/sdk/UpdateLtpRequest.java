package com.bajaj.sim.sdk;

import java.math.BigDecimal;

public class UpdateLtpRequest {
    private String symbol;
    private BigDecimal lastTradedPrice;

    public UpdateLtpRequest() {}

    public UpdateLtpRequest(String symbol, BigDecimal lastTradedPrice) {
        this.symbol = symbol;
        this.lastTradedPrice = lastTradedPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(BigDecimal lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }
}