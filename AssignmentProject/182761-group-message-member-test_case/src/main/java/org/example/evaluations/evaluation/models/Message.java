package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    private Long id;

    private String content;

    @ManyToOne
    private Member author;

    @ManyToOne
    private WhatsappGroup group;
}
