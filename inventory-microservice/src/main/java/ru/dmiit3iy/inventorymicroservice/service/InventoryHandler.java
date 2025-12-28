package ru.dmiit3iy.inventorymicroservice.service;


import lombok.RequiredArgsConstructor;

import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductByNameRequest;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductRequest;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductResponse;
import org.dmiit3iy.ordermicroservice.grpc.inventory.ReduceQuantityByNameRequest;
import org.springframework.stereotype.Service;
import ru.dmiit3iy.inventorymicroservice.model.Product;
import ru.dmiit3iy.inventorymicroservice.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class InventoryHandler {

    private final ProductRepository productRepository;

    public ProductResponse checkAvailability(ProductRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice().toString())
                .setSale(product.getSale().toString())
                .build();
    }

    public ProductResponse checkAvailability(ProductByNameRequest request) {
        Product product = productRepository.findByNameIgnoreCase(request.getName())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice().toString())
                .setSale(product.getSale().toString())
                .build();
    }

    public ProductResponse reduceQuantityByName(ReduceQuantityByNameRequest request) {
        Product product = productRepository.findByNameIgnoreCase(request.getName())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Not enough products in stock");
        }

        product.setQuantity(product.getQuantity() - request.getQuantity());
        productRepository.save(product);

        return ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setQuantity(product.getQuantity())
                .setPrice(product.getPrice().toString())
                .setSale(product.getSale().toString())
                .build();
    }
}