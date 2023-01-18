package com.rubic.cube.controller.mapper;

import com.rubic.cube.controller.model.request.CreateProductRequest;
import com.rubic.cube.controller.model.request.UpdateProductRequest;
import com.rubic.cube.controller.model.response.ProductResponse;
import com.rubic.cube.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse productToProductResponse(Product product);

    List<ProductResponse> productListToProductResponseList(List<Product> productList);

    Product createProductRequestToProduct(CreateProductRequest createProductRequest);

    Product updateProductRequestToProduct(UpdateProductRequest updateProductRequest);

}
