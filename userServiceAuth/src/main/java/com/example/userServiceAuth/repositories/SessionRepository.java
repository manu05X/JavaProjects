package com.example.userServiceAuth.repositories;


import com.example.userServiceAuth.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByUser_Id(Long userId);
    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
}