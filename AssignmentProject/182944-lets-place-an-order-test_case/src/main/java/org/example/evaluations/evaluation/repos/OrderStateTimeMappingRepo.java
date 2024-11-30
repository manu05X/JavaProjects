package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Order;
import org.example.evaluations.evaluation.models.OrderStateTimeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStateTimeMappingRepo extends JpaRepository<OrderStateTimeMapping,Long> {
    Optional<OrderStateTimeMapping> findByOrder(Order order);
}
