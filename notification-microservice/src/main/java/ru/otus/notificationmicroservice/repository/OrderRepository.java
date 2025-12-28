package ru.otus.notificationmicroservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.notificationmicroservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByOrderId(Long orderId, Pageable pageable);

    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
