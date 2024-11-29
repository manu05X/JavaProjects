package org.example.evaluations.repos;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.repos.CategoryRepo;
import org.example.evaluations.evaluation.repos.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Test
    @Transactional
    void testDeleteById() {
        //Arrange
        Product product = new Product();
        product.setName("Tablet");
        product.setId(25L);
        Product savedProduct = productRepo.save(product);

        //Act
        productRepo.deleteById(savedProduct.getId());

        //Assert
        Optional<Product> deletedProduct = productRepo.findById(savedProduct.getId());
        assert(deletedProduct.isEmpty());
    }

    @Test
    @Transactional
    void testDeleteAll() {
        //Arrange
        Category category = new Category();
        category.setName("Electronics");
        category.setId(1L);
        Category savedCategory = categoryRepo.save(category);

        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setCategory(savedCategory);
        product1.setId(22L);
        productRepo.save(product1);

        Product product2 = new Product();
        product2.setName("Smartphone");
        product2.setCategory(savedCategory);
        product2.setId(42L);
        productRepo.save(product2);

        //Act
        productRepo.deleteAll();

        //Assert
        long count = productRepo.count();
        assertEquals(0,count);
    }

    @Test
    @Transactional
    void testDeleteByName() {
        //Arrange
        Product product = new Product();
        product.setName("Monitor");
        product.setId(28L);
        productRepo.save(product);

        //Act
        Long deletedCount = productRepo.deleteByName("Monitor");

        //Assert
        assertThat(deletedCount).isEqualTo(1);
        List<Product> deletedProducts = productRepo.findByName("Monitor");
        assertEquals(0,deletedProducts.size());
    }

    @Test
    @Transactional
    void testDeleteByCategoryName() {
        //Arrange
        Category category = new Category();
        category.setName("Accessories");
        category.setId(5L);
        Category savedCategory = categoryRepo.save(category);

        Product product1 = new Product();
        product1.setName("Headphones");
        product1.setId(32L);
        product1.setCategory(savedCategory);
        productRepo.save(product1);

        Product product2 = new Product();
        product2.setName("Charger");
        product2.setId(52L);
        product2.setCategory(savedCategory);
        productRepo.save(product2);

        //Act
        productRepo.deleteByCategoryName("Accessories");

        //Assert
        long count = productRepo.count();
        assertEquals(0,count);
    }

    @Test
    @Transactional
    void testDeleteCategoriesWhereIdMatchesProductId() {
        //Arrange
        Category category = new Category();
        category.setName("Gaming");
        category.setId(10L);
        Category savedCategory = categoryRepo.save(category);

        Product product = new Product();
        product.setName("Console");
        product.setId(10L);
        product.setCategory(savedCategory);
        productRepo.save(product);

        //Act
        productRepo.deleteProductWhereIdMatchesCategoryId(savedCategory.getId());

        //Assert
        long count = productRepo.count();
        assertEquals(0,count);
    }

    @Test
    @Transactional
    void testDeleteCategoriesWhereIdDoesntMatchesAnyProductId() {
        //Arrange
        Category category = new Category();
        category.setName("Gaming");
        category.setId(10L);
        Category savedCategory = categoryRepo.save(category);

        Product product = new Product();
        product.setName("Console");
        product.setId(107L);
        product.setCategory(savedCategory);
        productRepo.save(product);

        Product product2 = new Product();
        product2.setName("Console");
        product2.setId(117L);
        product2.setCategory(savedCategory);
        productRepo.save(product2);

        //Act
        productRepo.deleteProductWhereIdMatchesCategoryId(savedCategory.getId());

        //Assert
        long count = productRepo.count();
        assertEquals(2,count);
    }

    @Test
    @Transactional
    void testRetainProductsAfter() {
        Product product1 = new Product();
        product1.setName("Old Product");
        product1.setId(1212L);
        product1.setCreatedAt(new Date(System.currentTimeMillis() - 10000000L));
        productRepo.save(product1);

        Product product2 = new Product();
        product2.setName("New Product");
        product2.setId(2121L);
        product2.setCreatedAt(new Date());
        productRepo.save(product2);

        Date retainDate = new Date(System.currentTimeMillis() - 5000000L);

        int deletedCount = productRepo.retainProductsAfter(retainDate);

        assertThat(deletedCount).isGreaterThan(0);

        List<Product> remainingProducts = productRepo.findAll();
        assertThat(remainingProducts).allMatch(product -> product.getCreatedAt().after(retainDate));
    }

    @Test
    @Transactional
    void testDeletingAllProductsBelongingToParticularCategoryAlsoResultInDeletionOfCategory() {
        //Arrange
        Category category = new Category();
        category.setName("Gaming");
        category.setId(10101L);

        Product product = new Product();
        product.setName("Console");
        product.setId(10700L);
        product.setCategory(category);
        productRepo.save(product);


        //Act
        productRepo.deleteById(10700L);

        //Assert
        long productCount = productRepo.count();
        long categoryCount = categoryRepo.count();
        assertEquals(0,productCount);
        assertEquals(0,categoryCount);
    }
}
