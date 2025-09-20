package dev.aratax.messaging.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import dev.aratax.messaging.kafka.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderEventsListener {

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void handle(ConsumerRecord<String, OrderEvent> event) {
        log.info("Received order from Kafka - key={}, partition={}, offset={}, value={}",
                event.key(),
                event.partition(),
                event.offset(),
                event.value());
    }

}
