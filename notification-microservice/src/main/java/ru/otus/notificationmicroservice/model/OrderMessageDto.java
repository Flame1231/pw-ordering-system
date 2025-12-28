package ru.otus.notificationmicroservice.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderMessageDto {
    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal sale;
    private BigDecimal totalPrice;
    private Long userId;
}
