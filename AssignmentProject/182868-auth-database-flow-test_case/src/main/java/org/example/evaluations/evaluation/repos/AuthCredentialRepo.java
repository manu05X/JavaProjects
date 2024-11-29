package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.AuthCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthCredentialRepo extends JpaRepository<AuthCredential,String> {
    Optional<AuthCredential> findAuthCredentialByEmail(String email);
}
