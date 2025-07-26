package org.example.betting.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.betting.model.Bet;
import org.example.betting.model.EventOutcome;
import org.example.betting.producer.BetSettlementProducer;
import org.example.betting.service.BetService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventOutcomeConsumer {

    // Kafka
    private static final String TOPIC = "event-outcomes";
    private static final String GROUP_ID = "bet-group";

    // Data Members
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final BetService betService;
    private final BetSettlementProducer betSettlementProducer;

    public EventOutcomeConsumer(BetService betService, BetSettlementProducer betSettlementProducer) {
        this.betService = betService;
        this.betSettlementProducer = betSettlementProducer;
    }

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(String message) throws Exception {
        // deserialize the msg
        EventOutcome outcome = objectMapper.readValue(message, EventOutcome.class);

        // Get the bets for the event
        List<Bet> bets = betService.getBetsForEvent(outcome.getEventId());
        if (bets.isEmpty()) {
            System.out.printf("No bets found for event %s, skipping settlement.\n", outcome.getEventId());
            return;
        }

        // send rabbitmq message to settle bets for the event
        // NOTE: We can also send a single message for each bet
        betSettlementProducer.sendMessage(outcome);

    }

}
