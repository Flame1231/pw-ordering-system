package org.dmiit3iy.ordermicroservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderEvent {
    private Long orderId;
    private Long userId;
    private Long productId;
    private int quantity;
    private BigDecimal price;
    private BigDecimal sale;
    private BigDecimal totalPrice;
}