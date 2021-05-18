package com.example.thuchanh3.Model;

public class Item {
    private int id;
    private String name;
    private Float price;
    private String image;
    private String type;

    public Item(){

    }

    public Item(String name, Float price, String image, String type) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }
}

