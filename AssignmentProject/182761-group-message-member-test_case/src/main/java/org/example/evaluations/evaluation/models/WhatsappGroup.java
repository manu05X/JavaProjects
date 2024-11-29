package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class WhatsappGroup {
    @Id
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<Member> members;

    @OneToMany(mappedBy = "group")
    private List<Message> messages;
}
