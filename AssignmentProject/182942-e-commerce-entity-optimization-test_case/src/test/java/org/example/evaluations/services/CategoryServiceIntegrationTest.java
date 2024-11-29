package org.example.evaluations.services;

import jakarta.transaction.Transactional;
import org.example.evaluations.evaluation.CustomStatementInspector;
import org.example.evaluations.evaluation.repos.CategoryRepo;
import org.example.evaluations.evaluation.services.CategoryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@SpringJUnitConfig
public class CategoryServiceIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomStatementInspector statementInspector;

    @Autowired
    private CategoryRepo categoryRepo;

    @BeforeEach
    public void setUpForEachMethod() {
        statementInspector.clear();
    }

    @BeforeAll
    public void setUpForClass() {
        jdbcTemplate.execute("INSERT INTO Category (id, state, title) VALUES (1, 0, 'Electronics')");
        jdbcTemplate.execute("INSERT INTO Category (id, state, title) VALUES (2, 0, 'Home Appliances')");
        jdbcTemplate.execute("INSERT INTO sub_category (id, state, name, description, category_id) VALUES (1, 0, 'Mobile Phones', 'Smartphones and accessories', 1)");
        jdbcTemplate.execute("INSERT INTO sub_category (id, state, name, description, category_id) VALUES (2, 0, 'Tablets', 'Tablets', 1)");
        jdbcTemplate.execute("INSERT INTO sub_category (id, state, name, description, category_id) VALUES (3, 0, 'Earpods', 'Headphones and Earphones', 1)");
        jdbcTemplate.execute("INSERT INTO sub_category (id, state, name, description, category_id) VALUES (4, 0, 'Grinders', 'Mixers and Grinders', 2)");
        jdbcTemplate.execute("INSERT INTO Product (id, state, name, category_id) VALUES (1, 0, 'iPhone 14', 1)");
        jdbcTemplate.execute("INSERT INTO Product (id, state, name, category_id) VALUES (2, 0, 'MacBook Pro', 1)");
        jdbcTemplate.execute("INSERT INTO Product (id, state, name, category_id) VALUES (3, 0, 'Washing Machine', 2)");
        jdbcTemplate.execute("INSERT INTO Image (id, state, resolution, size_in_kb, product_id, descriptive_name) VALUES (1, 0, '1920x1080', 500, 1, 'Front View of iPhone 14')");
        jdbcTemplate.execute("INSERT INTO Image (id, state, resolution, size_in_kb, product_id, descriptive_name) VALUES (2, 0, '2560x1600', 600, 2, 'Side View of MacBook Pro')");
        jdbcTemplate.execute("INSERT INTO Image (id, state, resolution, size_in_kb, product_id, descriptive_name) VALUES (3, 0, '1920x1600', 800, 3, 'Washing Machine From Top')");
    }

    @Test
    @Transactional
    public void testGetNamesOfAllCategoriesAndLinkedProductsAndTheirImages_WithFetchModeSubSelect() {
        //Act
        categoryService.getNamesOfAllCategoriesAndLinkedProductsAndTheirImages();

        //Assert
        List<String> executedQueries = statementInspector.getExecutedQueries();
        assertTrue(executedQueries.size()==3,"Only 3 queries are expected out of which 2 will be subqueries and one will be select query from Category_ table");
        assertTrue(executedQueries.get(1).equals("select pl1_0.category_id,pl1_0.id,pl1_0.name,pl1_0.state from product pl1_0 where pl1_0.category_id in (select c1_0.id from category c1_0)"),"expected subselect in query");
        assertTrue(executedQueries.get(2).equals("select i1_0.product_id,i1_0.id,i1_0.descriptive_name,i1_0.resolution,i1_0.size_in_kb,i1_0.state from image i1_0 where i1_0.product_id in (select pl1_0.id from product pl1_0 where pl1_0.category_id in (select c1_0.id from category c1_0))"),"expected subselects in query");
    }

    @Test
    @Transactional
    public void testGetNamesOfAllCategoriesAndTheirSubCategories_WithFetchModeSelectAndBatchSize3() {
        //Act
        categoryService.getNamesOfAllCategoriesAndTheirSubCategories();

        //Assert
        List<String> executedQueries = statementInspector.getExecutedQueries();
        assertTrue(executedQueries.size()==2,"Only 2 select queries are expected");
        assertTrue(executedQueries.get(1).equals("select sc1_0.category_id,sc1_0.id,sc1_0.description,sc1_0.name,sc1_0.state from sub_category sc1_0 where sc1_0.category_id in (?,?,?)"),"Expected a select query with batch size 3");
    }
}


