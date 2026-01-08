package com.bajaj.sim.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TradingClientTest {

    @Test
    public void testClientCreation() {
        TradingClient client = new TradingClient("http://localhost:8080");
        assertNotNull(client);
    }

}