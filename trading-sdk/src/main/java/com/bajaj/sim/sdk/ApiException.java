package com.bajaj.sim.sdk;

public class ApiException extends Exception {
    private int statusCode;
    private String responseBody;

    public ApiException(int statusCode, String responseBody) {
        super("API error: " + statusCode + " - " + responseBody);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}