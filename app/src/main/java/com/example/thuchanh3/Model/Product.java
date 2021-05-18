package com.example.thuchanh3.Model;

import androidx.annotation.NonNull;

public class Product {

    public Product() {
    }

    private int id;
    private String name;
    private int prices;
    private String image;
    private String category;

    public Product(int id, String name, int prices, String image, String category) {
        this.id = id;
        this.name = name;
        this.prices = prices;
        this.image = image;
        this.category = category;
    }

    public Product(String name, int prices, String image, String category) {
        this.name = name;
        this.prices = prices;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
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

    @NonNull
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prices=" + prices +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
