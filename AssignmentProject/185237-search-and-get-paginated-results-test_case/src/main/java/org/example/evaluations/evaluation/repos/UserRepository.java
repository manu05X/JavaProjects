package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Sex;
import org.example.evaluations.evaluation.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findUserByAddressEquals(String address, Pageable pageable);

    List<User> findUserBySexEquals(Sex sex, Pageable pageable);

    List<User> findUserBySexAndAgeGreaterThanEqual(Sex sex, Integer integer, Pageable pageable);
}
