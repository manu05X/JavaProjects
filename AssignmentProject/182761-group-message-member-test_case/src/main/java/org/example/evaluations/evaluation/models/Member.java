package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Member {
    @Id
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="members_whatsapp_groups",joinColumns = @JoinColumn(name="member_id"),inverseJoinColumns = @JoinColumn(name="whatsapp_group_id"))
    private Set<WhatsappGroup> groups=new HashSet<>();

    @OneToMany(mappedBy = "author")
    private List<Message> messages=new ArrayList<>();
}
