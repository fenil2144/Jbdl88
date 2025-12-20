package com.example.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "PRODUCT-APP")
@FeignClient(name = "product-app-client", url = "http://localhost:8080")
public interface ProductFeignClient {

    @PostMapping("/products")
    ProductDTO saveProduct(@RequestBody ProductDTO productDTO);

    @GetMapping("/products/{productId}")
    ProductDTO getProductById(@PathVariable long productId);
}
