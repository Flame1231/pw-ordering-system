package ru.otus.notificationmicroservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "product_id")
    private long productId;

    private int quantity;

    @NonNull
    private BigDecimal price;

    @NonNull
    private BigDecimal sale;

    @Column(name = "total_price")
    @NonNull
    private BigDecimal totalPrice;

    @Column(name = "user_id")
    private long userId;
}
