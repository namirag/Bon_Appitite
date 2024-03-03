package com.example.bonappetit.models;

public class RecipesModel {
    String image;
    String name;
    String ingredients;
    String method;

    public RecipesModel(String image, String name, String ingredients, String method) {
        this.image = image;
        this.name = name;
        this.ingredients = ingredients;
        this.method=method;
    }

    public String  getImage() {
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
