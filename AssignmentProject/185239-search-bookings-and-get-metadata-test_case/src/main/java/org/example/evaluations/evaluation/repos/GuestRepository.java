package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest,String> {
     Optional<Guest> findGuestByFirstNameAndLastName(String fName, String lName);
}
