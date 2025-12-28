package ru.dmiit3iy.inventorymicroservice.mapper;

import org.mapstruct.Mapper;
import ru.dmiit3iy.inventorymicroservice.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(Product product);
}
