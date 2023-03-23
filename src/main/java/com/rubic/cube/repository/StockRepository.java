package com.rubic.cube.repository;

import com.rubic.cube.entity.Product;
import com.rubic.cube.entity.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProduct(Product product);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "4000")})
    Optional<Stock> findByProduct_Id(Long productId);

    List<Stock> findAllByProduct_Code(String code, Pageable pageable);

}
