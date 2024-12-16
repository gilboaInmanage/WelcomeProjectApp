package com.example.welcomeprojectapp.server_responses;

import com.example.welcomeprojectapp.data.ContentPage;
import com.example.welcomeprojectapp.data.Language;
import com.example.welcomeprojectapp.data.StoreFeature;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseGeneralDeclarationResponse;


public class GeneralDeclarationResponse extends BaseGeneralDeclarationResponse {
    private List<Language> languages;
    private List<ContentPage> contentPages;
    private List<StoreFeature> features;

    public void parseData(JSONObject response) {
        try {
            // Parse languagesArr
            JSONArray languagesArray = Parser.jsonParse(response, "languagesArr", new JSONArray());
            languages = Parser.createList(languagesArray, new Language());

            // Parse content_pagesArr
            JSONArray contentPagesArray = Parser.jsonParse(response, "content_pagesArr", new JSONArray());
            contentPages = Parser.createList(contentPagesArray, new ContentPage());

            // Parse featuresArr
            JSONArray featuresArray = Parser.jsonParse(response, "featuresArr", new JSONArray());
            features = Parser.createList(featuresArray, new StoreFeature());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Getters for the parsed data
    public List<Language> getLanguages() {
        return languages;
    }

    public List<ContentPage> getContentPages() {
        return contentPages;
    }

    public List<StoreFeature> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "GeneralDeclarationResponse{" +
                "languages=" + languages +
                ", contentPages=" + contentPages +
                ", features=" + features +
                '}';
    }
}
