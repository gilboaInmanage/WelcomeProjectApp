package com.example.welcomeprojectapp.models;

public class Item {
    private final String title;
    private final String imageUrl;

    public Item(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

