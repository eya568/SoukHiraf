package com.example.myapplication;

public class Artisan {
    private String name;
    private String craft;
    private String rating;
    private String price;
    private int imageResId;
    public Artisan(String name, String craft, String rating, String price, int imageResId) {
        this.name = name;
        this.craft = craft;
        this.rating = rating;
        this.price = price;
        this.imageResId = imageResId;
    }
    public String getName() {
        return name;
    }
    public String getCraft() {
        return craft;
    }
    public String getRating() {
        return rating;
    }
    public String getPrice() {
        return price;
    }
    public int getImageResId() {
        return imageResId;
    }
}
