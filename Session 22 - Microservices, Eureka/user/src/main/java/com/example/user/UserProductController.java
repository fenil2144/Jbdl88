package com.example.user;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserProductController {

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    ProductFeignClient productFeignClient;

//    public static String PRODUCT_APP_URL = "http://localhost:8080/products";

    // Calls with Rest Template
//    @PostMapping
//    ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
//        log.info("In UserProductController.saveProduct with productDTO: {}", productDTO);
//        return restTemplate.postForEntity(PRODUCT_APP_URL, productDTO, ProductDTO.class);
//
//    }
//
//    @GetMapping("/{productId}")
//    ResponseEntity<ProductDTO> getProductById(@PathVariable long productId) {
//        log.info("In UserProductController.getProductById with productId: {}", productId);
//        String url = PRODUCT_APP_URL + productId;
//        ProductDTO productDTO = restTemplate.getForObject(url, ProductDTO.class);
//        return ResponseEntity.ok(productDTO);
//    }

    @PostMapping
    ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO){
        log.info("In UserProductController.saveProduct with productDTO: {}", productDTO);
        return ResponseEntity.ok(productFeignClient.saveProduct(productDTO));
    }

    @GetMapping("/{productId}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable long productId){
        log.info("In UserProductController.getProductById with productId: {}", productId);
        return ResponseEntity.ok(productFeignClient.getProductById(productId));
    }
}
