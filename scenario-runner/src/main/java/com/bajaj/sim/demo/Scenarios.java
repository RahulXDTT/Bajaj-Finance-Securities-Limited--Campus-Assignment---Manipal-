package com.bajaj.sim.demo;

import com.bajaj.sim.sdk.*;

import java.math.BigDecimal;
import java.util.List;

public class Scenarios {

    public static void runDemo(TradingClient client) throws Exception {
        // 1) list instruments
        List<Instrument> instruments = client.listInstruments();
        System.out.println("Instruments: " + instruments);

        // 2) place MARKET BUY
        PlaceOrderRequest req = new PlaceOrderRequest("TCS", Side.BUY, Style.MARKET, 10, null);
        PlaceOrderResponse resp = client.placeOrder(req);
        System.out.println("Order placed: " + resp.getOrderId() + " status: " + resp.getStatus());

        // 3) get order status
        Order order = client.getOrder(resp.getOrderId());
        System.out.println("Order status: " + order.getStatus());

        // 4) list trades
        List<Trade> trades = client.listTrades();
        System.out.println("Trades: " + trades);

        // 5) get portfolio
        List<Holding> portfolio = client.getPortfolio();
        System.out.println("Portfolio: " + portfolio);

        // 6) place LIMIT SELL that does not execute
        PlaceOrderRequest sellReq = new PlaceOrderRequest("TCS", Side.SELL, Style.LIMIT, 5, new BigDecimal("5000.00"));
        PlaceOrderResponse sellResp = client.placeOrder(sellReq);
        System.out.println("Sell order: " + sellResp.getOrderId() + " status: " + sellResp.getStatus());

        // 7) update LTP to trigger execution
        client.updateLtp("TCS", new BigDecimal("4500.00"));

        // 8) list trades + portfolio again
        trades = client.listTrades();
        System.out.println("Trades after: " + trades);
        portfolio = client.getPortfolio();
        System.out.println("Portfolio after: " + portfolio);
    }

}