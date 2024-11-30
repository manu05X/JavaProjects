package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Customer {
    @Id
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customers_addresses",joinColumns = @JoinColumn(name = "customer_id"),inverseJoinColumns = @JoinColumn(name="address_id"))
    private List<Address> addresses;

    private String email;

    private String password;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Order> orders;
}
