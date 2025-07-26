package org.example.betting.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.betting.model.EventOutcome;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventOutcomeProducer {

    private static final String TOPIC = "event-outcomes";
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final KafkaTemplate<String, String> kafkaTemplate;


    public EventOutcomeProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes an EventOutcome to the Kafka topic.
     */
    public void publish(EventOutcome eventOutcome) throws Exception {
        kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(eventOutcome));
    }

}
