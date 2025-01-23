package com.example.ECommerce.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
/*
The implementation uses auto-increment:

Uses GenerationType.AUTO which typically defaults to an auto-incrementing sequence
The database determines the exact strategy based on its capabilities
Generates sequential numeric IDs
Simpler setup but potential limitations in distributed systems
 */




/*
@MappedSuperclass
public class BaseModel {
    @Id
        @GeneratedValue(generator = "generator_name")
        @GenericGenerator(name = "generator_name", strategy = "uuid2")
        @Column(name = "id", columnDefinition = "binary(16)", updatable = false, nullable = false)
    private Long id;
}


The implementation uses UUID generation:

Uses @GenericGenerator with uuid2 strategy to generate unique IDs across distributed systems
The ID column is defined as binary(16) to efficiently store UUIDs
This approach is particularly useful for distributed systems where multiple servers might be generating IDs simultaneously
The updatable = false and nullable = false constraints ensure ID integrity



 */