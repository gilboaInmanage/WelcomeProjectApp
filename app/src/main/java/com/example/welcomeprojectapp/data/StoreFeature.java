package com.example.welcomeprojectapp.data;

import com.example.welcomeprojectapp.server_responses.GeneralDeclarationResponse;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.SortResponse;
import org.json.JSONObject;

public class StoreFeature extends SortResponse {

    public static final int FAVORITE_STORE_FEATURE_CODE = -100;
    public static final int OPEN_STORE_FEATURE_CODE = -101;

    private String title;
    private String imageOn;
    private String imageOff;
    private int featureCode;
    private int sortOrder;
    private boolean isSelected;

    public StoreFeature() {
        // Default constructor
    }

    public StoreFeature(int constFeatureCode, GeneralDeclarationResponse generalDeclarationResponse) {
        switch (constFeatureCode) {
            case FAVORITE_STORE_FEATURE_CODE:
                this.featureCode = FAVORITE_STORE_FEATURE_CODE;
                this.title = generalDeclarationResponse.getTranslationsMap().get("mobile_feauters_favorite");
                this.sortOrder = -1;
                break;

            case OPEN_STORE_FEATURE_CODE:
                this.featureCode = OPEN_STORE_FEATURE_CODE;
                this.title = generalDeclarationResponse.getTranslationsMap().get("mobile_branch_openOpen");
                this.sortOrder = 0;
                this.isSelected = true;

                break;

            default:
                break;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageOn() {
        return imageOn;
    }

    public void setImageOn(String imageOn) {
        this.imageOn = imageOn;
    }

    public String getImageOff() {
        return imageOff;
    }

    public void setImageOff(String imageOff) {
        this.imageOff = imageOff;
    }

    public int getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(int featureCode) {
        this.featureCode = featureCode;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public SortResponse createSortResponse(JSONObject jsonObject) {
        StoreFeature storeFeature = new StoreFeature();
        if (jsonObject != null) {
            storeFeature.setTitle(Parser.jsonParse(jsonObject, "title", ""));
            storeFeature.setFeatureCode(Parser.jsonParse(jsonObject, "FeaturesCode", 0));
            storeFeature.setSortOrder(Parser.jsonParse(jsonObject, "SortOrder", 0));
        }
        return storeFeature;
    }

    @Override
    public String toString() {
        return "StoreFeature{" +
                "title='" + title + '\'' +
                ", imageOn='" + imageOn + '\'' +
                ", imageOff='" + imageOff + '\'' +
                ", featureCode=" + featureCode +
                ", sortOrder=" + sortOrder +
                ", isSelected=" + isSelected +
                '}';
    }
}

