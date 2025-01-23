package com.example.ECommerce.repositories;

import com.example.ECommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Product findByTitleAndPrice_currency(String title, String currency);

    @Query(value = "select * from product where title = :title", nativeQuery = true)
    Product findByTitle(String title);

//    @Query(value = "select Product from Product where Product.title = :title", nativeQuery = false)
//    Product findByTitle2(String title);

//    Page<Product> findAllByTitle(String title, Pageable pageable);
//
//    Page<Product> findAllByTitleContaining(String title, Pageable pageable);
}
