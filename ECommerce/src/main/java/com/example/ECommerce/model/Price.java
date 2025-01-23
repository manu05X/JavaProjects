package com.example.ECommerce.model;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Price extends BaseModel {
    private String currency;
    private double price;

    // Explicit parameterized constructor
    public Price(String currency, double price) {
        this.currency = currency;
        this.price = price;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}