package com.rubic.cube.controller;

import com.rubic.cube.controller.mapper.ProductMapper;
import com.rubic.cube.controller.model.request.CreateProductRequest;
import com.rubic.cube.controller.model.request.UpdateProductRequest;
import com.rubic.cube.controller.model.response.CountModelResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProductController.PRODUCT_CONTROLLER_ADDRESS)
@RequiredArgsConstructor
@Validated
public class ProductController {

    public static final String PRODUCT_CONTROLLER_ADDRESS = "/products";
    public static final String STOCK_URL = "/stock";
    public static final String ORDER_ADDRESS = "/order";
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
    public Page<ProductResponse> findAllByCode(@PathVariable("code") String code,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        return productService.findAllByCode(code, page, size)
                .map(productMapper::productToProductResponse);
    }

    @GetMapping("/by-code/{code}/count")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds products count filtered by product code.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public CountModelResponse countAllByCode(@PathVariable("code") String code) {
        return new CountModelResponse(productService.countByCode(code));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds all products information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public Page<ProductResponse> findAll(@RequestParam("page") int page,
                                         @RequestParam("size") int size) {
        return productService.findAll(page, size)
                .map(productMapper::productToProductResponse);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "GET", description = "It finds count of all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public CountModelResponse countAll() {
        return new CountModelResponse(productService.countAll());
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
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_ALREADY_EXISTS_MSG)
    })
    public IdModelResponse create(@RequestBody @Validated CreateProductRequest createProductRequest) {
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
    public IdModelResponse update(@RequestBody @Validated UpdateProductRequest updateProductRequest) {
        Product newProduct = productMapper.updateProductRequestToProduct(updateProductRequest);
        Long id = productService.update(newProduct);
        return new IdModelResponse(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete", description = "It deletes a product by product id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_NOT_FOUND_MSG)
    })
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

    @PostMapping("/{id}" + ORDER_ADDRESS)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create", description = "Order a product for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_NOT_FOUND_MSG)
    })
    public IdModelResponse order(@PathVariable("id") Long id,
                                 @RequestParam Long userId) {
        Long orderId = productService.order(id, userId);
        return new IdModelResponse(orderId);
    }

    @PatchMapping("/{id}" + ORDER_ADDRESS)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Patch", description = "Reduce order count")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Edited"),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.PRODUCT_NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.USER_NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500", description = ExceptionMessage.ORDER_ITEM_NOT_FOUND_MSG)
    })
    public void reduceOrderItemByOne(@PathVariable("id") Long id, @RequestParam Long userId) {
        productService.reduceOrderByOne(id, userId);
    }

}
