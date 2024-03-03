package com.example.bonappetit.models;

@SuppressWarnings("ALL")
public class detailed_daily_meal_model {
    int image;
    String name;
    String des;
    String rating;
    String price;
    String timing;

    public detailed_daily_meal_model(int image, String name, String des, String rating, String price, String timing) {
        this.image = image;
        this.name = name;
        this.des = des;
        this.rating = rating;
        this.price = price;
        this.timing = timing;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
}
