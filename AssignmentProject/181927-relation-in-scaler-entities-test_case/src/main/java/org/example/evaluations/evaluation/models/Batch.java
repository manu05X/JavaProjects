package org.example.evaluations.implementation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Batch {
    @Id
    private UUID id;

    @ManyToMany(mappedBy = "batches")
    private List<Instructor> instructors;

    private String name;

    @OneToMany(mappedBy = "currentBatch")
    private Set<Learner> learners = new HashSet<>();

    @ManyToMany
    @JoinTable(name="batches_classes",joinColumns = @JoinColumn(name="batch_id"),inverseJoinColumns = @JoinColumn(name="class_id"))
    private Set<Class> classes;
}
