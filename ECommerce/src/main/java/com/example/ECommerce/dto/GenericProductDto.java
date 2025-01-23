package com.example.ECommerce.dto;

import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Price;
import com.example.ECommerce.thirdPartyClient.FakeStoreDto.FakeStoreProductDTO;

import java.io.Serializable;

public class GenericProductDto implements Serializable {
    /*
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

     */

    private Long id;
    private String title;
    private Price price; // Use Double for handling fractional values
    private String description;
    private Category category;
    private String image; // Add the image field
    private FakeStoreProductDTO.RatingDTO rating; // Use a nested class to map the `rating` object

    // Getters and Setters

    public static class RatingDTO {
        private Double rate; // Use Double for the rating value
        private int count;

        // Getters and Setters
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and Setters for outer class
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

    public FakeStoreProductDTO.RatingDTO getRating() {
        return rating;
    }

    public void setRating(FakeStoreProductDTO.RatingDTO rating) {
        this.rating = rating;
    }
}