package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class InstagramPage {
    @Id
    private UUID id;

    @OneToMany(mappedBy = "instagramPage")
    private Set<InstagramPost> posts = new HashSet<>();

    @ManyToOne
    private InstagramUser creator;
}

