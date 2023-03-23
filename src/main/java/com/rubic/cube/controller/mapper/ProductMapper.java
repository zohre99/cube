package com.rubic.cube.controller.mapper;

import com.rubic.cube.controller.model.request.CreateProductRequest;
import com.rubic.cube.controller.model.request.UpdateProductRequest;
import com.rubic.cube.controller.model.response.ProductResponse;
import com.rubic.cube.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "availableCount", source = "stock.availableCount")
    ProductResponse toProductResponse(Product product);

    Product toProduct(CreateProductRequest createProductRequest);

    Product toProduct(UpdateProductRequest updateProductRequest);

}
