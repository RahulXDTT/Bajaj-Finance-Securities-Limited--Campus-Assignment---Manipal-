package com.bajaj.sim.api.controller;

import com.bajaj.sim.api.model.*;
import com.bajaj.sim.api.service.TradingService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TradingController {
    private final TradingService service;

    public TradingController(TradingService service) {
        this.service = service;
    }

    @GetMapping("/instruments")
    public List<Instrument> listInstruments() {
        return service.listInstruments();
    }

    @PostMapping("/orders")
    public PlaceOrderResponse placeOrder(@Valid @RequestBody PlaceOrderRequest req) {
        return service.placeOrder(req);
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return service.getOrder(orderId);
    }

    @GetMapping("/trades")
    public List<Trade> listTrades() {
        return service.listTrades();
    }

    @GetMapping("/portfolio")
    public List<Holding> getPortfolio() {
        return service.getPortfolio();
    }

    @PostMapping("/admin/ltp")
    public void updateLtp(@Valid @RequestBody UpdateLtpRequest req) {
        service.updateLtp(req);
    }
}