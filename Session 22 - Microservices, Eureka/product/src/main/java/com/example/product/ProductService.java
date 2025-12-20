package com.example.product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private AtomicLong nextId = new AtomicLong(0);
    private Map<Long, Product> productMap = new HashMap<>();

    public @Nullable Product saveProduct(Product product) {

        product.setId(nextId.incrementAndGet());
        productMap.put(product.getId(), product);
        return product;
    }

    public @Nullable Product getProduct(long productId) {
        return productMap.get(productId);
    }
}
