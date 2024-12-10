package com.example.welcomeprojectapp.server_responses;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseGeneralDeclarationResponse;

import org.json.JSONObject;

public class GeneralDeclarationResponse extends BaseGeneralDeclarationResponse {

    private boolean showBanner;
    private boolean isImage;
    private String imageUrl;
    private String videoUrl;

    @Override
    public void parseData(JSONObject response) {
        // Parse JSON data
        JSONObject bannerData = Parser.jsonParse(response, "banner", new JSONObject());
        this.showBanner = Parser.jsonParse(bannerData, "show_banner", false);
        this.isImage = Parser.jsonParse(bannerData, "isImage", false);
        this.imageUrl = Parser.jsonParse(bannerData, "imageUrl", "");
        this.videoUrl = Parser.jsonParse(bannerData, "videoUrl", "");
    }

    // Getters
    public boolean isShowBanner() {
        return showBanner;
    }

    public boolean isImage() {
        return isImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public String toString() {
        return "GeneralDeclarationResponse{" +
                "showBanner=" + showBanner +
                ", isImage=" + isImage +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
