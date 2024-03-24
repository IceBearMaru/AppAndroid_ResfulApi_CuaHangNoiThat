package com.example.noithat.Model;

public class Product {
    private int id;
    private String name;
    private String material;
    private String origin;
    private String imageUrl;
    private double price;

    public Product(int id, String name, String material, String origin, String imageUrl, double price) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.origin = origin;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Product( String name, String material, String origin, String imageUrl, double price) {
        this.name = name;
        this.material = material;
        this.origin = origin;
        this.imageUrl = imageUrl;
        this.price = price;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}