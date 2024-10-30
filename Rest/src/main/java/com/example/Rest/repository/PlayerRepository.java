package com.example.Rest.repository;

import com.example.Rest.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Integer> {
}

/*
We will create an interface PlayerRepository.java which extends the JpaRepository interface and provides the entity and the data type of the primary key(<Player, Integer>).
Simply by extending the JpaRepository, we get all basic CRUD operations like findAll(), findById(), save(), and deleteById() etc., without having to write any implementation.

Simply by extending the JpaRepository, we get all basic CRUD operations like findAll(), findById(), save(), and deleteById() etc., without having to write any implementation.

* */