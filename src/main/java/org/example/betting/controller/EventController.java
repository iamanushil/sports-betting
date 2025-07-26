package org.example.betting.controller;

import org.example.betting.config.KafkaConfig;
import org.example.betting.model.EventOutcome;
import org.example.betting.producer.EventOutcomeProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventOutcomeProducer eventOutcomeProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> publishEvent(@RequestBody EventOutcome eventOutcome) {
        try {
            logger.info("Received event outcome: {}", eventOutcome);
            eventOutcomeProducer.publish(eventOutcome);
            return ResponseEntity.ok("Event outcome published successfully to Kafka");
        } catch (Exception e) {
            logger.error("Failed to publish event outcome", e);
            return ResponseEntity.status(500).body("Failed to publish event outcome: " + e.getMessage());
        }
    }

}
