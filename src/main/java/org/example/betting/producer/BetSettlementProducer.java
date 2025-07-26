package org.example.betting.producer;

import org.example.betting.model.EventOutcome;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BetSettlementProducer {

    // RabbitMQ
    @Value("${spring.rabbitmq.exchange}")
    private String EXCHANGE;

    @Value("${spring.rabbitmq.routing-key}")
    private String ROUTING_KEY;

    private final RabbitTemplate rabbitTemplate;

    public BetSettlementProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Sends a message to RabbitMQ
     */
    public void sendMessage(EventOutcome outcome) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, outcome);
        System.out.printf("Sent message to RabbitMQ: %s\n", outcome);
    }


}
