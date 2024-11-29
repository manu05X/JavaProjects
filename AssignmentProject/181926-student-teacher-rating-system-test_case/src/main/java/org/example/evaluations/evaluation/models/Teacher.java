package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Teacher {

    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherRating> ratings;
}
