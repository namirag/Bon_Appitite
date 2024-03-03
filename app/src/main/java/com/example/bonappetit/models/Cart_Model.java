package com.example.bonappetit.models;

public class Cart_Model {
    int image;
    String name;
    String price;
    String quantity;

    public Cart_Model(int image, String name, String price, String quantity) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {return quantity;}

    public void setQuantity(String quantity) {this.quantity = quantity;}
}
