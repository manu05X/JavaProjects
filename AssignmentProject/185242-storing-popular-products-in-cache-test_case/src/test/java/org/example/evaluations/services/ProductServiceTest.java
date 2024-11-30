package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private RedisTemplate<String,Object> redisTemplate;

   @Mock
    private RedisConnectionFactory redisConnectionFactory;

   @Mock
    private RedisConnection redisConnectionMock;

   @Mock
   private HashOperations hashOperations;


   @Mock
   private SetOperations setOperations;


    @BeforeEach
    public void setUp() {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        when(redisConnectionFactory.getConnection()).thenReturn(redisConnectionMock);
        redisTemplate.afterPropertiesSet();
    }

    @Test
    void testGetPopularProducts() {
        Set<Object> productIdsObject = new HashSet<>();
        productIdsObject.add("1");
        productIdsObject.add("2");

        when(redisTemplate.opsForSet()).thenReturn(setOperations);
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);

        when(setOperations.members("iphone_related")).thenReturn(productIdsObject);

        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("iPhone 13");
        product1.setDescription("Latest iPhone model.");
        product1.setCategory("Smartphone");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("iPhone 12");
        product2.setDescription("Previous generation iPhone.");
        product2.setCategory("Smartphone");

        when(hashOperations.get("products", "1")).thenReturn(product1);
        when(hashOperations.get("products", "2")).thenReturn(product2);

        Set<Product> popularProducts = productService.getPopularProducts();

        assert(popularProducts.size() == 2);
        assert(popularProducts.contains(product1));
        assert(popularProducts.contains(product2));
    }

    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setId(3L);
        product.setTitle("New iPhone");
        product.setDescription("Amazing iPhone.");
        product.setCategory("Smartphone");

        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(redisTemplate.opsForSet()).thenReturn(setOperations);

        Product addedProduct = productService.addProduct(product);


        assert(addedProduct.equals(product));
        verify(redisTemplate.opsForSet(),times(1)).add("iphone_related", product.getId());

    }

    @Test
    void testAddNonIphoneProduct() {
        Product product = new Product();
        product.setId(4L);
        product.setTitle("Samsung Galaxy");
        product.setDescription("Popular Android phone.");
        product.setCategory("Android");

        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(redisTemplate.opsForSet()).thenReturn(setOperations);

        Product addedProduct = productService.addProduct(product);


        assert(addedProduct.equals(product));

        verify(redisTemplate.opsForSet(),times(0)).add("iphone_related", product.getId());

    }
}

