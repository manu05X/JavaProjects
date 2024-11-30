package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Slice<Product> findProductByWeightLessThanEqual(Double weight, Pageable pageable);

    Slice<Product> findProductByCategory(String category, Pageable pageable);

    Page<Product> findProductByName(String name, Pageable pageable);
}
