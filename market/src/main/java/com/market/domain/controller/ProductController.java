package com.market.domain.controller;

import com.market.domain.Product;
import com.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation("Get all products")
    @ApiResponse(code=200,message = "OK")
    public ResponseEntity<List<Product> > getall() {
        return new ResponseEntity<>(productService.getall(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get product by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404,message = "Error"),
    })

    public ResponseEntity<Product> getProduct(@ApiParam(value ="el id del producto", required = true,example = "433")
                                                  @PathVariable("id") Integer id){return productService.getProduct(id)
            .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));}

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int id){
        return productService.getByCategory(id).map(
                products -> new ResponseEntity<>(products,HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); }

    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.ACCEPTED);}

    @DeleteMapping("/id")
    private ResponseEntity delete(@PathVariable("id") int id){
        if(productService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }}
}

