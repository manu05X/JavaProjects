package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Inventory;
import org.example.evaluations.evaluation.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByItem(Item item);

    Inventory save(Inventory inventory);
}
