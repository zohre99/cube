package com.rubic.cube.controller.mapper;

import com.rubic.cube.controller.model.request.CreateProductRequest;
import com.rubic.cube.controller.model.request.UpdateProductRequest;
import com.rubic.cube.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock toStock(CreateProductRequest createProductRequest);

    Stock toStock(UpdateProductRequest updateProductRequest);

}
