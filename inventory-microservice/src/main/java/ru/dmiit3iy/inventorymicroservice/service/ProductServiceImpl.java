package ru.dmiit3iy.inventorymicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmiit3iy.inventorymicroservice.mapper.ProductMapper;
import ru.dmiit3iy.inventorymicroservice.model.Product;
import ru.dmiit3iy.inventorymicroservice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product add(Product product) {
        if(productRepository.existsByName(product.getName())){
            throw new IllegalArgumentException("Product name already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public Product get(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> get() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> get(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);

    }

    @Override
    public Product delete(long id) {
        Product product = get(id);
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public Product update(Product product) {
        Product productBase = get(product.getId());
        productBase = productMapper.toProduct(product);
        return add(productBase);
    }
}
