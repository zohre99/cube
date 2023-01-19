package com.rubic.cube.repository;

import com.rubic.cube.controller.model.response.ProductStockByColorResponse;
import com.rubic.cube.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCode(String code, Pageable pageable);

    @Query("SELECT new com.rubic.cube.controller.model.response.ProductStockByColorResponse(p.color, sum(p.stock)) from Product p where p.code=:code group by p.color")
    List<ProductStockByColorResponse> findStockByCode(String code);

}
