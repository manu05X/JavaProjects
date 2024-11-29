package org.example.evaluations.evaluation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TeacherRatingKey implements Serializable {

    @Column(name="student_id")
    private Long studentId;

    @Column(name="teacher_id")
    private Long teacherId;
}
