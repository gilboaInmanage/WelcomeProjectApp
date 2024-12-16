package com.example.welcomeprojectapp.models;

public class Item {
    private String type;
    private String description;

    public Item(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
