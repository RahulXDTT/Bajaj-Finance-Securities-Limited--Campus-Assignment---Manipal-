package com.bajaj.sim.api.model;

import java.math.BigDecimal;

public class Instrument {
    private String symbol;
    private String exchange;
    private InstrumentType instrumentType;
    private BigDecimal lastTradedPrice;

    public Instrument() {}

    public Instrument(String symbol, String exchange, InstrumentType instrumentType, BigDecimal lastTradedPrice) {
        this.symbol = symbol;
        this.exchange = exchange;
        this.instrumentType = instrumentType;
        this.lastTradedPrice = lastTradedPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public BigDecimal getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(BigDecimal lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }
}