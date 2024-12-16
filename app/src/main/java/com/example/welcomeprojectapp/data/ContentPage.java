package com.example.welcomeprojectapp.data;

import org.json.JSONObject;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.SortResponse;

public class ContentPage extends SortResponse {

    private String id = "";
    private String title = "";
    private String image = "";
    private int imgDrawableRes = 0;
    private String route = "";

    public ContentPage() {
        // Default constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImgDrawableRes() {
        return imgDrawableRes;
    }

    public void setImgDrawableRes(int imgDrawableRes) {
        this.imgDrawableRes = imgDrawableRes;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public SortResponse createSortResponse(JSONObject jsonObject) {
        ContentPage contentPage = new ContentPage();
        if (jsonObject != null) {
            contentPage.setId(Parser.jsonParse(jsonObject, "id", Parser.createTempString()));
            contentPage.setTitle(Parser.jsonParse(jsonObject, "title", Parser.createTempString()));
            contentPage.setImage(Parser.jsonParse(jsonObject, "image", Parser.createTempString()));
            contentPage.setRoute(Parser.jsonParse(jsonObject, "react_route", Parser.createTempString()));
        }
        return contentPage;
    }

    @Override
    public String toString() {
        return "ContentPage{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", imgDrawableRes=" + imgDrawableRes +
                ", route='" + route + '\'' +
                '}';
    }
}
