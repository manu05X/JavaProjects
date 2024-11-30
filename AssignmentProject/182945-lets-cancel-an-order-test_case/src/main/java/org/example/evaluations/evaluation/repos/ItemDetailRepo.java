package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.ItemDetail;
import org.example.evaluations.evaluation.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDetailRepo extends JpaRepository<ItemDetail,Long> {
    ItemDetail save(ItemDetail itemDetail);
    List<ItemDetail> findByOrder(Order order);
}
