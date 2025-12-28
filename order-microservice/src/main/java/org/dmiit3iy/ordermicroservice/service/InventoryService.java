package org.dmiit3iy.ordermicroservice.service;

import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.dmiit3iy.ordermicroservice.client.InventoryClient;

import org.dmiit3iy.ordermicroservice.grpc.inventory.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryClient inventoryClient;

    /**
     * Получение продукта по имени.
     * @param name имя продукта
     * @return Optional<ProductResponse>
     */
    public Optional<ProductResponse> getProductByName(String name) {
        try {
            ProductResponse response = inventoryClient.checkProductByName(name);
            return Optional.ofNullable(response);
        } catch (StatusRuntimeException e) {
            // gRPC ошибка
            System.err.println("gRPC error while fetching product by name: " + e.getStatus());
            return Optional.empty();
        } catch (Exception e) {
            // общая ошибка
            System.err.println("Error while fetching product by name: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Уменьшение количества продукта по имени.
     * @param name имя продукта
     * @param quantity количество для уменьшения
     * @return Optional<ProductResponse>
     */
    public Optional<ProductResponse> reduceProductQuantityByName(String name, int quantity) {
        try {
            ProductResponse response = inventoryClient.reduceQuantityByName(name, quantity);
            return Optional.ofNullable(response);
        } catch (StatusRuntimeException e) {
            System.err.println("gRPC error while reducing quantity: " + e.getStatus());
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error while reducing product quantity: " + e.getMessage());
            return Optional.empty();
        }
    }
}