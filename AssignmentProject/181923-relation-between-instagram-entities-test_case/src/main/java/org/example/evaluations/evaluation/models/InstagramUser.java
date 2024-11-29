package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.*;

@Entity
public class InstagramUser {
    @Id
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "creator")
    private Set<InstagramPage> pages = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<InstagramLike> instagramLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<InstagramComment> instagramComments = new ArrayList<>();
}
