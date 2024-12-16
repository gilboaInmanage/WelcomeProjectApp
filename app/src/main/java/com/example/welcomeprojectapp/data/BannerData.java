package com.example.welcomeprojectapp.data;

import android.util.Log;

import org.json.JSONObject;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseResponse;

public class BannerData extends BaseResponse {

    private String title = "";
    private String content = "";
    private String contentImage = "";
    private boolean shouldShowBanner = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public boolean isShouldShowBanner() {
        return shouldShowBanner;
    }

    public void setShouldShowBanner(boolean shouldShowBanner) {
        this.shouldShowBanner = shouldShowBanner;
    }

    @Override
    public BaseResponse createResponse(JSONObject jsonObject) {
        Log.d("BannerData", "createResponse: " + jsonObject);
        BannerData bannerData = new BannerData();
        if (jsonObject == null || jsonObject.length() == 0) {
            return bannerData;
        }

        bannerData.setShouldShowBanner(jsonObject.length() != 0);

        JSONObject titleArr = Parser.jsonParse(jsonObject, "titleArr", new JSONObject());
        JSONObject contentArr = Parser.jsonParse(jsonObject, "contentArr", new JSONObject());

        bannerData.setTitle(Parser.jsonParse(titleArr, "content", ""));
        bannerData.setContent(Parser.jsonParse(contentArr, "content", ""));
        bannerData.setContentImage(Parser.jsonParse(contentArr, "image", ""));

        Log.d("BannerData", "bannerData: " + bannerData);
        return bannerData;
    }

    @Override
    public String toString() {
        return "BannerData{" +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", contentImage='" + contentImage + '\'' +
                ", shouldShowBanner=" + shouldShowBanner +
                '}';
    }
}
