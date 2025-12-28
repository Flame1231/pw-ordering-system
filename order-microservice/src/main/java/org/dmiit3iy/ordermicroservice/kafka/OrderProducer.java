package org.dmiit3iy.ordermicroservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.model.dto.OrderEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${topic.name}")
    private String topic;

    public void send(OrderEvent orderEvent) {
        try {
            String orderJson = new ObjectMapper().writeValueAsString(orderEvent);
            kafkaTemplate.send(topic, orderJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize order event", e);
        }
    }
}
