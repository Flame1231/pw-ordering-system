package org.dmiit3iy.ordermicroservice.client;


import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dmiit3iy.ordermicroservice.grpc.inventory.*;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InventoryClient {

    @GrpcClient("inventory")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub;

    // Получение продукта по ID
    public ProductResponse checkProductById(Long productId) {
        ProductRequest request = ProductRequest.newBuilder()
                .setProductId(productId)
                .build();
        return inventoryStub.checkAvailability(request);
    }

    // Получение продукта по имени
    public ProductResponse checkProductByName(String name) {
        ProductByNameRequest request = ProductByNameRequest.newBuilder()
                .setName(name)
                .build();
        return inventoryStub.checkAvailabilityByName(request);
    }

    // Уменьшение quantity продукта по имени
    public ProductResponse reduceQuantityByName(String name, int quantity) {
        ReduceQuantityByNameRequest request = ReduceQuantityByNameRequest.newBuilder()
                .setName(name)
                .setQuantity(quantity)
                .build();
        return inventoryStub.reduceQuantityByName(request);
    }
}