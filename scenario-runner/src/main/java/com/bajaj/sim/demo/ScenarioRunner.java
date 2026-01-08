package com.bajaj.sim.demo;

import com.bajaj.sim.sdk.TradingClient;

import java.io.InputStream;
import java.util.Properties;

public class ScenarioRunner {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        try (InputStream is = ScenarioRunner.class.getResourceAsStream("/demo.properties")) {
            props.load(is);
        }
        String baseUrl = props.getProperty("baseUrl", "http://localhost:8080");
        TradingClient client = new TradingClient(baseUrl);

        Scenarios.runDemo(client);
    }

}