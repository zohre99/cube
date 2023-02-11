package com.rubic.cube.repository;

import com.rubic.cube.controller.model.response.ProductStockByColorResponse;
import com.rubic.cube.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCode(String code, Pageable pageable);

    Long countByCode(String code);

    @Query("SELECT new com.rubic.cube.controller.model.response.ProductStockByColorResponse(p.color, sum(p.stock)) from Product p where p.code=:code group by p.color")
    List<ProductStockByColorResponse> findStockByCode(String code);

    Optional<Product> findByCodeAndNameAndColor(String code, String name, String color);

    List<Product> findByStockLessThan(Integer stock);

    @Lock(LockModeType.PESSIMISTIC_WRITE) //TODO: see why exactly LockModeType.PESSIMISTIC_READ results deadlock
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "4000")})
    Optional<Product> findById(Long id);

}
