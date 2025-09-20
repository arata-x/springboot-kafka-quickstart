package dev.aratax.messaging.kafka.controller;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.aratax.messaging.kafka.model.OrderEvent;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderEventController {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping("/orders")
    public ResponseEntity<String> create(@Valid @RequestBody OrderEvent event) throws Exception {
        event.setId(UUID.randomUUID().toString());
        event.setCreatedAt(Instant.now());
        CompletableFuture<SendResult<String, OrderEvent>> future = kafkaTemplate.sendDefault(event.getId(), event);
        SendResult<String, OrderEvent> result = future.get(1000, TimeUnit.MILLISECONDS);
        log.info("Sent order to Kafka - key={}, partition={}, offset={}, value={}",
                result.getProducerRecord().key(),
                result.getRecordMetadata().partition(),
                result.getRecordMetadata().offset(),
                result.getProducerRecord().value());
        return ResponseEntity.ok().body("Order Creation Completed");
    }

}
