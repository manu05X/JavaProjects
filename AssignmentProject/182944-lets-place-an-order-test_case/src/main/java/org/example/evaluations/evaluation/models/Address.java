package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Address extends BaseModel {
    private String number;
    private String street;
    private String city;
    private String pincode;
    private String landmark;
    private String state;
    private Boolean isDefault;

    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customers;
}
