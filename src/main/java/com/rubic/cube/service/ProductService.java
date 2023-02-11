package com.rubic.cube.service;

import com.rubic.cube.controller.model.response.ProductStockByColorResponse;
import com.rubic.cube.entity.Product;
import com.rubic.cube.entity.User;
import com.rubic.cube.exception.BusinessCodeException;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.rubic.cube.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderItemService orderItemService;
    private final UserService userService;

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BusinessCodeException(PRODUCT_NOT_FOUND, PRODUCT_NOT_FOUND_MSG));
    }

    public Page<Product> findAllByCode(String code, int page, int size) {
        return productRepository.findAllByCode(code, Pageable.ofSize(size).withPage(page));
    }

    public Long countByCode(String code) {
        return productRepository.countByCode(code);
    }

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    public Long countAll() {
        return productRepository.count();
    }

    public List<ProductStockByColorResponse> findStockByCode(String code) {
        return productRepository.findStockByCode(code);
    }

    public Long create(Product product) {
        Optional<Product> optionalProduct = productRepository.findByCodeAndNameAndColor(product.getCode(),
                product.getName(), product.getColor());
        if (optionalProduct.isPresent()) {
            throw new BusinessCodeException(PRODUCT_ALREADY_EXISTS, PRODUCT_ALREADY_EXISTS_MSG);
        }
        return productRepository.save(product).getId();
    }

    @Transactional
    public Long update(Product newProduct) {
        Optional<Product> oldProduct = productRepository.findById(newProduct.getId());
        if (!oldProduct.isPresent()) {
            throw new BusinessCodeException(PRODUCT_NOT_FOUND, PRODUCT_NOT_FOUND_MSG);
        }
        Product product = oldProduct.get();

        Optional<Product> optionalSimilarProduct = productRepository.findByCodeAndNameAndColor(product.getCode(),
                newProduct.getName(), newProduct.getColor());
        if (optionalSimilarProduct.isPresent()) {
            throw new BusinessCodeException(PRODUCT_CONFLICT, PRODUCT_CONFLICT_MSG);
        }

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

    // @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    @Transactional
    public Long order(Long productId, Long userId) {
        User user = userService.findById(userId);
        Product product = findById(productId);

        System.out.println("\nuserId:" + userId);

        if (product.getStock() < 1) {
            throw new BusinessCodeException(ExceptionMessage.SOLED_OUT, ExceptionMessage.SOLED_OUT_MSG);
        }

        Long orderId = orderItemService.orderProduct(product, user);
        product.setStock(product.getStock() - 1);
        productRepository.save(product);
        return orderId;
    }

    @Transactional
    public void reduceOrderByOne(Long productId, Long userId) {
        Product product = findById(productId);
        User user = userService.findById(userId);

        orderItemService.reduceOrderCountByOne(product, user);

        product.setStock(product.getStock() + 1);
        productRepository.save(product);
    }

}
