package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private String PRODUCT_IN_DEMAND = "iphone";
    private final String REDIS_KEY_POPULAR_PRODUCT = "iphone_related";

    public Set<Product> getPopularProducts() {
        Set<Object> productIdsObject = redisTemplate.opsForSet().members(REDIS_KEY_POPULAR_PRODUCT);
        Set<Product> products = new HashSet<>();

        Set<String> productIds = productIdsObject.stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        if (productIds != null) {
            for (String id : productIds) {
                products.add((Product) redisTemplate.opsForHash().get("products", id));
            }
        }

        return products;
    }

    public Product addProduct(Product product) {
        redisTemplate.opsForHash().put("products",product.getId(),product);

        if (product.getTitle().toLowerCase().contains(PRODUCT_IN_DEMAND) ||
                product.getDescription().toLowerCase().contains(PRODUCT_IN_DEMAND) ||
                product.getCategory().toLowerCase().contains(PRODUCT_IN_DEMAND)) {
            redisTemplate.opsForSet().add(REDIS_KEY_POPULAR_PRODUCT, product.getId());
        }

        return product;
    }
}
