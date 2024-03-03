package com.example.bonappetit.models;

public class daily_meal_model {
    String image;
    String name;
    String description;
    String type;
    String rating;

    public daily_meal_model(String image, String name, String description, String type, String rating) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.type = type;
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
