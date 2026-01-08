# Bajaj Trading Simulation

A simple trading simulation project with Spring Boot REST API, thin SDK wrapper, and demo scenario runner.

## Project Structure

- `trading-api-server`: Spring Boot REST backend
- `trading-sdk`: Thin wrapper SDK for API calls
- `scenario-runner`: Demo application using the SDK

## Setup Instructions

1. Ensure Java 13 is installed on your system.
2. Clone or download the project.
3. Navigate to the root directory: `cd bajaj-trading-sim`
4. Build the project: `mvn clean install`
5. Start the API server:
   - `cd trading-api-server`
   - `mvn spring-boot:run`
   - Server will start on http://localhost:8080
6. In another terminal, run the demo:
   - `cd scenario-runner`
   - `mvn exec:java -Dexec.mainClass="com.bajaj.sim.demo.ScenarioRunner"`

## API Endpoints

### Instruments
- `GET /api/v1/instruments` - List all instruments

### Orders
- `POST /api/v1/orders` - Place a new order
- `GET /api/v1/orders/{orderId}` - Get order details

### Trades
- `GET /api/v1/trades` - List all executed trades

### Portfolio
- `GET /api/v1/portfolio` - Get current holdings

### Admin (Optional)
- `POST /api/v1/admin/ltp` - Update last traded price for an instrument

## Curl Examples

```bash
# List instruments
curl -X GET http://localhost:8080/api/v1/instruments

# Place market buy order
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{"symbol":"TCS","side":"BUY","style":"MARKET","quantity":10}'

# Get order status
curl -X GET http://localhost:8080/api/v1/orders/1

# List trades
curl -X GET http://localhost:8080/api/v1/trades

# Get portfolio
curl -X GET http://localhost:8080/api/v1/portfolio

# Update LTP (admin)
curl -X POST http://localhost:8080/api/v1/admin/ltp \
  -H "Content-Type: application/json" \
  -d '{"symbol":"TCS","lastTradedPrice":4200.50}'
```

## Assumptions

- In-memory data storage (no database required)
- Single mock user (userId = "user1")
- No authentication required
- LTP updates are manual via admin endpoint
- Portfolio computed on-demand from trade history
- Simple order execution simulation:
  - MARKET orders execute immediately at current LTP
  - LIMIT orders execute only if price condition is met
- No concurrency handling or advanced patterns

## Technologies

- Java 13
- Spring Boot 2.x
- Maven
- Jackson for JSON
- SpringDoc OpenAPI for Swagger UI