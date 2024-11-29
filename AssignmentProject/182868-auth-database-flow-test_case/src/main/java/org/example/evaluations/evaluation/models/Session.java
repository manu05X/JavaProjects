package org.example.evaluations.evaluation.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name="UserLoginSessions")
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String token;

    private Date ttl;

    private SessionState sessionState;
}

