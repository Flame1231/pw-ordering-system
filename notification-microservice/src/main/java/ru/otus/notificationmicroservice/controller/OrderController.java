package ru.otus.notificationmicroservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.notificationmicroservice.model.Order;
import ru.otus.notificationmicroservice.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    @Operation(summary = "Получить список всех заказов")
    public ResponseEntity<Page<Order>> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "id") String sortBy,
                                                    @RequestParam(defaultValue = "ASC") String direction) {
        Page<Order> products = orderService.getAll(page, size, sortBy, direction);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{order_id}")
    @Operation(summary = "Получить список всех покупок по одному заказу")
    public ResponseEntity<Page<Order>> getAllByOrderId(@PathVariable("order_id") Long orderId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "ASC") String direction) {
        Page<Order> products = orderService.getById(orderId,page, size, sortBy, direction);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/user/{user_id}")
    @Operation(summary = "Получить список всех покупок id пользователя")
    public ResponseEntity<Page<Order>> getAllByUserId(@PathVariable("user_id") Long userId,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "ASC") String direction) {
        Page<Order> products = orderService.getById(userId,page, size, sortBy, direction);
        return ResponseEntity.ok(products);
    }
}
