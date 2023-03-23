package com.rubic.cube.service;


import com.rubic.cube.entity.Product;
import com.rubic.cube.entity.Stock;
import com.rubic.cube.exception.BusinessCodeException;
import com.rubic.cube.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.rubic.cube.exception.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository repository;

    public Stock findByProduct(Product product) {
        return repository.findByProduct(product)
                .orElseThrow(() -> new BusinessCodeException(STOCK_NOT_FOUND, STOCK_NOT_FOUND_MSG));
    }

    public Stock findByProductId(Long productId) {
        return repository.findByProduct_Id(productId)
                .orElseThrow(() -> new BusinessCodeException(STOCK_NOT_FOUND, STOCK_NOT_FOUND_MSG));
    }

    public Stock create(Stock stock) {
        if (repository.findByProduct(stock.getProduct()).isPresent()) {
            throw new BusinessCodeException(STOCK_ALREADY_EXISTS, STOCK_ALREADY_EXISTS_MSG);
        }
        return repository.save(stock);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void increaseRemainedByOne(Stock stock) {
        stock.setAvailableCount(stock.getAvailableCount() + 1);
        repository.save(stock);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reduceRemainedByOne(Stock stock) {
        stock.setAvailableCount(stock.getAvailableCount() - 1);
        repository.save(stock);
    }

}
