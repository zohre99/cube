package com.rubic.cube.controller;

import com.rubic.cube.controller.mapper.ProductMapper;
import com.rubic.cube.controller.model.request.CreateProductRequest;
import com.rubic.cube.controller.model.request.UpdateProductRequest;
import com.rubic.cube.controller.model.response.IdResponseModel;
import com.rubic.cube.controller.model.response.ProductResponse;
import com.rubic.cube.entity.Product;
import com.rubic.cube.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductController.PRODUCT_CONTROLLER_ADDRESS)
@RequiredArgsConstructor
public class ProductController {
    public static final String PRODUCT_CONTROLLER_ADDRESS = "/products";

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return productMapper.productToProductResponse(product);
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAllByCode(@PathVariable("code") String code,
                                               @RequestParam("page") int page,
                                               @RequestParam("limit") int size) {
        List<Product> productList = productService.findAllByCode(code, page, size);
        return productMapper.productListToProductResponseList(productList);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAll(@RequestParam("page") int page,
                                         @RequestParam("limit") int size) {
        List<Product> productList = productService.findAll(page, size);
        return productMapper.productListToProductResponseList(productList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponseModel create(@RequestBody CreateProductRequest createProductRequest) {
        Product product = productMapper.createProductRequestToProduct(createProductRequest);
        Long id = productService.create(product);
        return new IdResponseModel(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public IdResponseModel update(@RequestBody UpdateProductRequest updateProductRequest) {
        Product newProduct = productMapper.updateProductRequestToProduct(updateProductRequest);
        Long id = productService.update(newProduct);
        return new IdResponseModel(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

}
