package ru.dmiit3iy.inventorymicroservice.service;


import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dmiit3iy.ordermicroservice.grpc.inventory.*;

@GrpcService
@RequiredArgsConstructor
public class GrpcInventoryServiceImpl extends InventoryServiceGrpc.InventoryServiceImplBase {

    private final InventoryHandler inventoryHandler;

    /**
     * Получение продукта по ID
     */
    @Override
    public void checkAvailability(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        try {
            ProductResponse response = inventoryHandler.checkAvailability(request);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * Получение продукта по имени
     */
    @Override
    public void checkAvailabilityByName(ProductByNameRequest request, StreamObserver<ProductResponse> responseObserver) {
        try {
            ProductResponse response = inventoryHandler.checkAvailability(request);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    /**
     * Уменьшение quantity продукта по имени
     */
    @Override
    public void reduceQuantityByName(ReduceQuantityByNameRequest request, StreamObserver<ProductResponse> responseObserver) {
        try {
            ProductResponse response = inventoryHandler.reduceQuantityByName(request);
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}