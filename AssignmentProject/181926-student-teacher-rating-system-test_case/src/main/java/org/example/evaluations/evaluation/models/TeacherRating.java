package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;

@Entity
public class TeacherRating {
    @EmbeddedId
    private TeacherRatingKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    private int rating;
}
