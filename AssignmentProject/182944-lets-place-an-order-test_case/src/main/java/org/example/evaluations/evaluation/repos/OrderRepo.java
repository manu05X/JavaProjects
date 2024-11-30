package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    Order save(Order order);
}
