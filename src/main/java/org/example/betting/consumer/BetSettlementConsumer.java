package org.example.betting.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.betting.model.EventOutcome;
import org.example.betting.service.BetService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BetSettlementConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final BetService betService;

    public BetSettlementConsumer(BetService betService) {
        this.betService = betService;
    }

    /**
     * Listens to the RabbitMQ queue for messages and processes them.
     */
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(EventOutcome eventOutcome) throws Exception {
        System.out.println("Received from RabbitMQ: " + eventOutcome);
        // Settle the bets for the event
        betService.settleBets(eventOutcome);
    }

}
