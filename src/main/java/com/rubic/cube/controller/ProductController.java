package com.rubic.cube.controller;

import com.rubic.cube.controller.mapper.ProductMapper;
import com.rubic.cube.controller.model.request.CreateProductRequest;
import com.rubic.cube.controller.model.request.UpdateProductRequest;
import com.rubic.cube.controller.model.response.IdModelResponse;
import com.rubic.cube.controller.model.response.ProductResponse;
import com.rubic.cube.controller.model.response.ProductStockByColorResponse;
import com.rubic.cube.entity.Product;
import com.rubic.cube.exception.ExceptionMessage;
import com.rubic.cube.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductController.PRODUCT_CONTROLLER_ADDRESS)
@RequiredArgsConstructor
public class ProductController {
    public static final String PRODUCT_CONTROLLER_ADDRESS = "/products";
    public static final String STOCK_URL = "/stock";

    private final ProductService productService;

    private final ProductMapper productMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds product information by product id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_NOT_FOUND_MSG)
    })
    public ProductResponse findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        return productMapper.productToProductResponse(product);
    }

    @GetMapping("/by-code/{code}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds products information filtered by product code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public List<ProductResponse> findAllByCode(@PathVariable("code") String code,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        List<Product> productList = productService.findAllByCode(code, page, size);
        return productMapper.productListToProductResponseList(productList);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds all products information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public List<ProductResponse> findAll(@RequestParam("page") int page,
                                         @RequestParam("size") int size) {
        List<Product> productList = productService.findAll(page, size);
        return productMapper.productListToProductResponseList(productList);
    }

    @GetMapping(STOCK_URL + "/{code}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds product stock; filter by product code and group by product color.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public List<ProductStockByColorResponse> findProductStock(@PathVariable("code") String code) {
        return productService.findStockByCode(code);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create", description = "It creates a new product with given info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
    })
    public IdModelResponse create(@RequestBody CreateProductRequest createProductRequest) {
        Product product = productMapper.createProductRequestToProduct(createProductRequest);
        Long id = productService.create(product);
        return new IdModelResponse(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update", description = "It updates a product with given info.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_NOT_FOUND_MSG)
    })
    public IdModelResponse update(@RequestBody UpdateProductRequest updateProductRequest) {
        Product newProduct = productMapper.updateProductRequestToProduct(updateProductRequest);
        Long id = productService.update(newProduct);
        return new IdModelResponse(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete", description = "It deletes a product by product id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_NOT_FOUND)
    })
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

}
