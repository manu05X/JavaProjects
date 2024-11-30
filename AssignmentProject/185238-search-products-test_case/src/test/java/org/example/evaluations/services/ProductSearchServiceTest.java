package org.example.evaluations.services;

import org.example.evaluations.evaluation.dtos.SortType;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.repos.ProductRepository;
import org.example.evaluations.evaluation.services.ProductSearchService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringJUnitConfig
public class ProductSearchServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public void setUpForClass() {
        jdbcTemplate.execute("INSERT INTO product (id, name, amount, weight, age, category) VALUES (1, 'Iphone 15', 100000, 26, 2.5, 'Electronics')");
        jdbcTemplate.execute("INSERT INTO product (id, name, amount, weight, age, category) VALUES (10, 'Iphone 15', 120000, 23, 2, 'Electronics')");
        jdbcTemplate.execute("INSERT INTO product (id, name, amount, weight, age, category) VALUES (2, 'Macbook Pro', 100000, 31, 4, 'Electronics')");
        jdbcTemplate.execute("INSERT INTO product (id, name, amount, weight, age, category) VALUES (3, 'Tommy Tshirt', 4500, 5, 2, 'Clothing')");
        jdbcTemplate.execute("INSERT INTO product (id, name, amount, weight, age, category) VALUES (4, 'Levis Jeans', 4500, 10, 1, 'Clothing')");
        jdbcTemplate.execute("INSERT INTO product (id, name, amount, weight, age, category) VALUES (6, 'Sweater', 8500, 10, 2, 'Clothing')");
    }



    @Test
    void testFindProductsWhichCanBeTakenInHandBaggageBecauseOfWeightLimit() {
        // Arrange
        Double allowedLimit = 100.0;
        Double actualWeight = 65.0;
        Integer pageNumber = 0;
        Integer pageSize = 7;

        // Act
        Slice<Product> result = productSearchService.findProductsWhichCanBeTakenInHandBaggageBecauseOfWeightLimit(allowedLimit, actualWeight, pageNumber, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(6, result.getContent().size());
        assertEquals("Iphone 15", result.getContent().get(0).getName());
        assertEquals(120000, result.getContent().get(0).getAmount());
        assertEquals("Macbook Pro", result.getContent().get(2).getName());
        assertEquals("Tommy Tshirt", result.getContent().get(4).getName());

    }

    @Test
    void testFindProductsBelongingToCategoryShownByAge() {
        // Arrange
        String category = "Clothing";
        Integer pageNumber = 0;
        Integer pageSize = 7;

        // Act
        Slice<Product> result = productSearchService.findProductsBelongingToCategoryShownByAge(category, pageNumber, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getContent().size());
        assertEquals("Levis Jeans", result.getContent().get(0).getName());
    }

    @Test
    void testFindProductsBySearchQuerySortedByUserProvidedCriteria() {
        // Arrange
        String query = "Iphone 15";
        Integer pageNumber = 0;
        Integer pageSize = 7;
        String sortParamName = "age";
        SortType sortType = SortType.ASC;

        // Act
        Page<Product> result = productSearchService.findProductsBySearchQuerySortedByUserProvidedCriteria(query, pageNumber, pageSize, sortParamName, sortType);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(120000, result.getContent().get(0).getAmount());
    }

    @Test
    void testFindProductsBySearchQuerySortedByUserProvidedCriteriaDescending() {
        // Arrange
        String query = "Iphone 15";
        Integer pageNumber = 0;
        Integer pageSize = 8;
        String sortParamName = "weight";
        SortType sortType = SortType.DESC;

        // Act
        Page<Product> result = productSearchService.findProductsBySearchQuerySortedByUserProvidedCriteria(query, pageNumber, pageSize, sortParamName, sortType);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(26, result.getContent().get(0).getWeight());
        assertEquals(10, result.getContent().get(1).getId());
    }
}
