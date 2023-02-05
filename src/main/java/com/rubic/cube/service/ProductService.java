package com.rubic.cube.service;

import com.rubic.cube.controller.model.response.ProductStockByColorResponse;
import com.rubic.cube.entity.Product;
import com.rubic.cube.exception.BusinessCodeException;
import com.rubic.cube.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.rubic.cube.exception.ExceptionMessage.PRODUCT_NOT_FOUND;
import static com.rubic.cube.exception.ExceptionMessage.PRODUCT_NOT_FOUND_MSG;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BusinessCodeException(PRODUCT_NOT_FOUND, PRODUCT_NOT_FOUND_MSG));
    }

    public List<Product> findAllByCode(String code, int page, int size) {
        return productRepository.findAllByCode(code, Pageable.ofSize(size).withPage(page))
                .toList();
    }

    public List<Product> findAll(int page, int size) {
        return productRepository.findAll(Pageable.ofSize(size).withPage(page))
                .toList();
    }

    public List<ProductStockByColorResponse> findStockByCode(String code) {
        return productRepository.findStockByCode(code);
    }

    public Long create(Product product) {
        return productRepository.save(product).getId();
    }

    @Transactional
    public Long update(Product newProduct) {
        Optional<Product> oldProduct = productRepository.findById(newProduct.getId());
        if (!oldProduct.isPresent()) {
            throw new BusinessCodeException(PRODUCT_NOT_FOUND, PRODUCT_NOT_FOUND_MSG);
        }
        Product product = oldProduct.get();

        if (newProduct.getName() != null) {
            product.setName(newProduct.getName());
        }
        if (newProduct.getColor() != null) {
            product.setColor(newProduct.getColor());
        }
        if (newProduct.getDescription() != null) {
            product.setDescription(newProduct.getDescription());
        }
        if (newProduct.getStock() != null) {
            product.setStock(newProduct.getStock());
        }

        productRepository.save(product);
        return product.getId();
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new BusinessCodeException(PRODUCT_NOT_FOUND, PRODUCT_NOT_FOUND_MSG);
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void reduceStockByOne(Product product) {
        product.setStock(product.getStock() - 1);
        productRepository.save(product);
    }

    @Transactional
    public void increaseStockByOne(Product product) {
        product.setStock(product.getStock() + 1);
        productRepository.save(product);
    }


}
