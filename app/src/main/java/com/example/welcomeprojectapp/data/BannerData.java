package com.example.welcomeprojectapp.data;

import android.util.Log;

import org.json.JSONObject;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseResponse;

public class BannerData extends BaseResponse {

    public static final String BACKGROUND_TYPE_IMAGE = "image";
    private String id = "";
    private String type = "";
    private String title = "";
    private String titleColor = "";
    private String content = "";
    private String contentColor = "";
    private String contentImage = "";
    private String buttonContent = "";
    private String buttonContentColor = "";
    private String buttonBackgroundColor = "";
    private String buttonUrl = "";
    private String backgroundType = "";
    private String backgroundImage = "";
    private String backgroundColor = "";
    private boolean shouldShowBanner = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentColor() {
        return contentColor;
    }

    public void setContentColor(String contentColor) {
        this.contentColor = contentColor;
    }

    public String getContentImage() {
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    public String getButtonContent() {
        return buttonContent;
    }

    public void setButtonContent(String buttonContent) {
        this.buttonContent = buttonContent;
    }

    public String getButtonContentColor() {
        return buttonContentColor;
    }

    public void setButtonContentColor(String buttonContentColor) {
        this.buttonContentColor = buttonContentColor;
    }

    public String getButtonBackgroundColor() {
        return buttonBackgroundColor;
    }

    public void setButtonBackgroundColor(String buttonBackgroundColor) {
        this.buttonBackgroundColor = buttonBackgroundColor;
    }

    public String getButtonUrl() {
        return buttonUrl;
    }

    public void setButtonUrl(String buttonUrl) {
        this.buttonUrl = buttonUrl;
    }

    public String getBackgroundType() {
        return backgroundType;
    }

    public void setBackgroundType(String backgroundType) {
        this.backgroundType = backgroundType;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
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
        bannerData.setId(Parser.jsonParse(jsonObject, "id", Parser.createTempString()));
        bannerData.setType(Parser.jsonParse(jsonObject, "type", Parser.createTempString()));

        JSONObject titleArr = Parser.jsonParse(jsonObject, "titleArr", new JSONObject());
        JSONObject contentArr = Parser.jsonParse(jsonObject, "contentArr", new JSONObject());
        JSONObject buttonArr = Parser.jsonParse(jsonObject, "buttonArr", new JSONObject());
        JSONObject backgroundArr = Parser.jsonParse(jsonObject, "backgroundArr", new JSONObject());

        bannerData.setTitle(Parser.jsonParse(titleArr, "content", ""));
        bannerData.setTitleColor(Parser.jsonParse(titleArr, "color", "#000000"));
        bannerData.setContent(Parser.jsonParse(contentArr, "content", ""));
        bannerData.setContentColor(Parser.jsonParse(contentArr, "color", "#000000"));
        bannerData.setContentImage(Parser.jsonParse(contentArr, "image", ""));
        bannerData.setButtonContent(Parser.jsonParse(buttonArr, "content", ""));
        bannerData.setButtonContentColor(Parser.jsonParse(buttonArr, "color", "#000000"));
        bannerData.setButtonBackgroundColor(Parser.jsonParse(buttonArr, "background_color", ""));
        bannerData.setButtonUrl(Parser.jsonParse(buttonArr, "url", ""));
        bannerData.setBackgroundType(Parser.jsonParse(backgroundArr, "background_type", ""));
        bannerData.setBackgroundImage(Parser.jsonParse(backgroundArr, "image", ""));
        bannerData.setBackgroundColor(Parser.jsonParse(backgroundArr, "color", ""));

        Log.d("BannerData", "bannerData: " + bannerData);
        return bannerData;
    }

    @Override
    public String toString() {
        return "BannerData{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", titleColor='" + titleColor + '\'' +
                ", content='" + content + '\'' +
                ", contentColor='" + contentColor + '\'' +
                ", contentImage='" + contentImage + '\'' +
                ", buttonContent='" + buttonContent + '\'' +
                ", buttonContentColor='" + buttonContentColor + '\'' +
                ", buttonBackgroundColor='" + buttonBackgroundColor + '\'' +
                ", buttonUrl='" + buttonUrl + '\'' +
                ", backgroundType='" + backgroundType + '\'' +
                ", backgroundImage='" + backgroundImage + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", shouldShowBanner=" + shouldShowBanner +
                '}';
    }
}
