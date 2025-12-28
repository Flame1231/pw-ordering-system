package ru.otus.notificationmicroservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.otus.notificationmicroservice.mapper.OrderMapper;
import ru.otus.notificationmicroservice.model.Order;
import ru.otus.notificationmicroservice.model.OrderMessageDto;
import ru.otus.notificationmicroservice.repository.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderListener {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final OrderMapper orderMapper;

    @KafkaListener(topics = "orders", groupId = "notification-group")
    public void listen(String message) {
        try {
            OrderMessageDto dto = objectMapper.readValue(message, OrderMessageDto.class);
            Order order = orderMapper.toOrder(dto);
            orderRepository.save(order);
            log.info("Сохранен заказ: {}", order);
        } catch (Exception e) {
            log.error("Ошибка при обработке Kafka-сообщения: {}", message, e);
        }
    }
}
