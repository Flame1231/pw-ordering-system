package org.dmiit3iy.ordermicroservice.controller;

import lombok.RequiredArgsConstructor;

import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductResponse;
import org.dmiit3iy.ordermicroservice.mapper.ProductMapper;
import org.dmiit3iy.ordermicroservice.model.dto.ProductDTO;
import org.dmiit3iy.ordermicroservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductMapper productMapper;

    /**
     * Получение продукта по имени.
     */
    @GetMapping("/search")
    public ResponseEntity<ProductDTO> getItemByName(@RequestParam String name) {
        return inventoryService.getProductByName(name)
                .map(productMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Уменьшение quantity продукта по имени.
     */
    @PostMapping("/reduce")
    public ResponseEntity<?> reduceQuantity(
            @RequestParam String name,
            @RequestParam int quantity
    ) {
        return inventoryService.reduceProductQuantityByName(name, quantity)
                .map(productMapper::toDto)
                .map(dto -> ResponseEntity.ok((Object) dto))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Failed to reduce quantity for product '" + name + "'"));
    }
}