package com.bajaj.sim.api.model;

public class PlaceOrderResponse {
    private Long orderId;
    private OrderStatus status;
    private String message;

    public PlaceOrderResponse() {}

    public PlaceOrderResponse(Long orderId, OrderStatus status, String message) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}