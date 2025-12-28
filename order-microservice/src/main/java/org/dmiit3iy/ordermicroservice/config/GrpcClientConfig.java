package org.dmiit3iy.ordermicroservice.config;

import net.devh.boot.grpc.client.inject.GrpcClient;


import org.dmiit3iy.ordermicroservice.grpc.inventory.InventoryServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @GrpcClient("inventory-service")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub;

    @Bean
    public InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub() {
        return inventoryStub;
    }
}
