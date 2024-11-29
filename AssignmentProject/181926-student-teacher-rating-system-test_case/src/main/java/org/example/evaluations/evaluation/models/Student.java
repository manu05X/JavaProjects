package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Student {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "students_teachers",joinColumns = @JoinColumn(name = "student_id"),inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "student")
    Set<TeacherRating> ratings;
}
