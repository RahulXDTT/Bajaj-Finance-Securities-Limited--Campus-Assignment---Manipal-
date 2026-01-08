package com.bajaj.sim.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TradingClient {
    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public TradingClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    public List<Instrument> listInstruments() throws ApiException {
        return get("/api/v1/instruments", new TypeReference<List<Instrument>>(){});
    }

    public PlaceOrderResponse placeOrder(PlaceOrderRequest req) throws ApiException {
        return post("/api/v1/orders", req, PlaceOrderResponse.class);
    }

    public Order getOrder(long orderId) throws ApiException {
        return get("/api/v1/orders/" + orderId, Order.class);
    }

    public List<Trade> listTrades() throws ApiException {
        return get("/api/v1/trades", new TypeReference<List<Trade>>(){});
    }

    public List<Holding> getPortfolio() throws ApiException {
        return get("/api/v1/portfolio", new TypeReference<List<Holding>>(){});
    }

    public void updateLtp(String symbol, BigDecimal ltp) throws ApiException {
        UpdateLtpRequest req = new UpdateLtpRequest(symbol, ltp);
        post("/api/v1/admin/ltp", req, Void.class);
    }

    private <T> T get(String path, Class<T> clazz) throws ApiException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .GET()
                .build();
        return send(req, clazz);
    }

    private <T> T get(String path, TypeReference<T> typeRef) throws ApiException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + path))
                .GET()
                .build();
        return send(req, typeRef);
    }

    private <T> T post(String path, Object body, Class<T> clazz) throws ApiException {
        try {
            String json = mapper.writeValueAsString(body);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + path))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .header("Content-Type", "application/json")
                    .build();
            return send(req, clazz);
        } catch (IOException e) {
            throw new ApiException(0, e.getMessage());
        }
    }

    private <T> T send(HttpRequest req, Class<T> clazz) throws ApiException {
        try {
            HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() >= 200 && resp.statusCode() < 300) {
                if (clazz == Void.class) {
                    return null;
                }
                return mapper.readValue(resp.body(), clazz);
            } else {
                throw new ApiException(resp.statusCode(), resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new ApiException(0, e.getMessage());
        }
    }

    private <T> T send(HttpRequest req, TypeReference<T> typeRef) throws ApiException {
        try {
            HttpResponse<String> resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() >= 200 && resp.statusCode() < 300) {
                return mapper.readValue(resp.body(), typeRef);
            } else {
                throw new ApiException(resp.statusCode(), resp.body());
            }
        } catch (IOException | InterruptedException e) {
            throw new ApiException(0, e.getMessage());
        }
    }
}