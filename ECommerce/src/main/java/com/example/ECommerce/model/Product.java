package com.example.ECommerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
public class Product extends BaseModel {
    private String title;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Price price;
    private String description;

    // As cardinality between product to category is ManyToOne so category class must have a List<Product>
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;
    private String image; // Add image URL
    @Embedded
    private Rating rating; // Use an embedded class for nested rating fields

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Embeddable
    public static class Rating {
        private Double rate; // Use Double for decimal values
        private int count;

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}


/*

{
    "id": 1,
    "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
    "price": 109.95,
    "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
    "category": "men's clothing",
    "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
    "rating": {
      "rate": 3.9,
      "count": 120
    }
  }

*/