package com.example.welcomeprojectapp.server_responses;

import org.json.JSONObject;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseGeneralDeclarationResponse;

public class GeneralDeclarationResponse extends BaseGeneralDeclarationResponse {

    private boolean showBanner;
    private boolean isImage;
    private String imageUrl = "https://upload.wikimedia.org/wikipedia/en/2/26/We_Are_Your_Friends.jpg";
    //private String videoUrl;

    @Override
    public void parseData(JSONObject response) {
        try {
            // Parse JSON data
            JSONObject bannerData = Parser.jsonParse(response, "banner", new JSONObject());
            this.showBanner = Parser.jsonParse(bannerData, "show_banner", false);
            this.isImage = Parser.jsonParse(bannerData, "isImage", false);

            // Validate and set image URL
            String parsedImageUrl = Parser.jsonParse(bannerData, "imageUrl", null);
            if (isValidUrl(parsedImageUrl)) {
                this.imageUrl = parsedImageUrl;
            }

            // Validate and set video URL
            //this.videoUrl = Parser.jsonParse(bannerData, "videoUrl", "");
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
        }
    }

    private boolean isValidUrl(String url) {
        return url != null && !url.isEmpty() && url.startsWith("http");
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


    @Override
    public String toString() {
        return "GeneralDeclarationResponse{" +
                "showBanner=" + showBanner +
                ", isImage=" + isImage +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
