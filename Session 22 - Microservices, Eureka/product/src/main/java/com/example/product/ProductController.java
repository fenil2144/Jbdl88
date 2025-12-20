package com.example.product;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        log.info("In ProductController.saveProduct with product: {}", product);
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping("/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable long productId) {
        log.info("In ProductController.getProductById with productId: {}", productId);
        return ResponseEntity.ok(productService.getProduct(productId));
    }
}
