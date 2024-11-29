package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class InstagramPost {
    @Id
    private UUID id;

    @ManyToOne
    private InstagramPage instagramPage;

    @OneToMany(mappedBy = "post")
    private List<InstagramLike> instagramLikes;

    @OneToMany(mappedBy = "post")
    private List<InstagramComment> instagramComments;

    private String content;
}

