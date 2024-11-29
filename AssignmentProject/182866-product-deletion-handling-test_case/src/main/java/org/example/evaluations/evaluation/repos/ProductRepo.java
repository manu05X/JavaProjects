package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);

    void deleteById(Long id);

    void deleteAll();

    Long deleteByName(String name);

    void deleteByCategoryName(String categoryName);

    @Modifying
    @Query("delete from Product p where p.id in (select c.id from Category c where c.id=?1)")
    void deleteProductWhereIdMatchesCategoryId(Long categoryId);

    @Modifying
    @Query("delete from Product p where p.createdAt < ?1")
    int retainProductsAfter(Date retainDate);
}
