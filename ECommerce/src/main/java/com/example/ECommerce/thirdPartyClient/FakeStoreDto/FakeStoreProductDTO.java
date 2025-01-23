package com.example.ECommerce.thirdPartyClient.FakeStoreDto;


import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Price;

public class FakeStoreProductDTO {
    private String title;
    private Price price; // Use Double for handling fractional values
    private String description;
    private Category category;
    private String image; // Add the image field
    private RatingDTO rating; // Use a nested class to map the `rating` object

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

    public RatingDTO getRating() {
        return rating;
    }

    public void setRating(RatingDTO rating) {
        this.rating = rating;
    }
}
