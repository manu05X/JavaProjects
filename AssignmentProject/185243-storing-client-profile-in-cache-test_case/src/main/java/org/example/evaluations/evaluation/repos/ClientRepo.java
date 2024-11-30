package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {

    Optional<Client> findClientByEmail(String email);
}
