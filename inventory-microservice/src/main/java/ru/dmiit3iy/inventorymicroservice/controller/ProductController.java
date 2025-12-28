package ru.dmiit3iy.inventorymicroservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmiit3iy.inventorymicroservice.model.Product;
import ru.dmiit3iy.inventorymicroservice.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Получить список всех продуктов")
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Page<Product> products = productService.get(page, size, sortBy, direction);
        return ResponseEntity.ok(products);

    }

    @Operation(summary = "Получить продукт по его id")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.get(productId));
    }

    @Operation(summary = "Получить продукт по его имени")
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        return ResponseEntity.ok(productService.getByName(name));
    }

    @Operation(summary = "Создать продукт")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @Operation(summary = "Удалить продукт по id")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long productId) {
        Product deletedProduct = productService.delete(productId);
        return ResponseEntity.ok(deletedProduct);
    }


}
