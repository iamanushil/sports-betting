# Sports Betting App

A sports betting platform built with Spring Boot, Kafka, and RabbitMQ using Docker.

## Overview
This application allows you to:
- Add and view bets
- Publish event outcomes
- Integrate with Kafka and RabbitMQ for messaging
- All services run in Docker containers for easy setup.

## Prerequisites
- Docker and Docker Compose

## Setup & Running

1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd sports-betting
   ```

2. **Start Using Docker Compose:**
   ```sh
   docker-compose up --build
   ```
   This will build the app and start Kafka, RabbitMQ, and the web application.

3. **Access the application:**
   - URL: `http://localhost:8080`


## API Usage

### View All Bets
```sh
curl --location 'http://localhost:8080/api/bets/all'
```

### Add a Bet
```sh
curl --location 'http://localhost:8080/api/bets/add' \
--header 'Content-Type: application/json' \
--data '{
    "betId": "bet4",
    "userId": "user4",
    "eventId": "match789",
    "eventMarketId": "market2",
    "eventWinnerId": "teamEngland",
    "betAmount": 250.0
}'
```

### Publish Event Outcome
```sh
curl --location 'http://localhost:8080/api/event/publish' \
--header 'Content-Type: application/json' \
--data '{
    "eventId": "match789",
    "eventName": "anushil",
    "winnerId": "teamIndia"
}'
```

## Notes
- If you change the code, rebuild with `docker-compose up --build`.
- For any issues, check logs with `docker-compose logs <service>`.