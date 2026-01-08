package com.bajaj.sim.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class UpdateLtpRequest {
    @NotBlank
    private String symbol;
    @NotNull
    private BigDecimal lastTradedPrice;

    public UpdateLtpRequest() {}

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