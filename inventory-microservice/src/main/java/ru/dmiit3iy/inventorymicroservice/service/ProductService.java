package ru.dmiit3iy.inventorymicroservice.service;

import org.springframework.data.domain.Page;
import ru.dmiit3iy.inventorymicroservice.model.Product;

import java.util.List;

public interface ProductService {
    Product add(Product product);

    Product get(long id);

    Product getByName(String name);

    List<Product> get();

    Page<Product> get(int page, int size, String sortBy, String direction);

    Product delete(long id);

    Product update(Product product);
}
