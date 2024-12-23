package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
public class InstagramLike {
    @Id
    private UUID id;

    @ManyToOne
    private InstagramPost post;

    @ManyToOne
    private InstagramUser user;
}
