package ru.otus.notificationmicroservice.service;

import org.springframework.data.domain.Page;
import ru.otus.notificationmicroservice.model.Order;

public interface OrderService {
    Page<Order> getAll(int page, int size, String sortBy, String direction);

    Page<Order> getById(Long orderId, int page,int size, String sortBy, String direction);

    Page<Order> getByUserId(Long userId, int page,int size, String sortBy, String direction);
    void save(Order order);
}
