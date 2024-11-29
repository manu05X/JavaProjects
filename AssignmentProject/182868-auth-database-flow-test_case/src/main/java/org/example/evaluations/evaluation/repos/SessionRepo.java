package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Session;
import org.example.evaluations.evaluation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session,Long> {
    Optional<Session> findSessionByUser(User user);
}
