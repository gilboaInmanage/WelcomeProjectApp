package com.example.welcomeprojectapp.server_responses;

import static com.example.welcomeprojectapp.applications.WelcomeApplication.app;

import android.util.Log;

import com.example.welcomeprojectapp.applications.WelcomeApplication;
import com.example.welcomeprojectapp.utils.EnvironmentUtils;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseGetHostUrlResponse;
import org.json.JSONObject;

public class GetHostUrlResponse extends BaseGetHostUrlResponse {

    private String firstTimeMessage;

    @Override
    public void parseData(JSONObject response) {
        super.parseData(response);
        JSONObject colorObject = Parser.jsonParse(response, "color", new JSONObject());
        int red = Parser.jsonParse(colorObject, "r", 255);
        int green = Parser.jsonParse(colorObject, "g", 255);
        int blue = Parser.jsonParse(colorObject, "b", 255);
        this.setColorHeaderHex(String.format("#%02x%02x%02x", red, green, blue));
        setRelevantUrls(response);
        this.firstTimeMessage = Parser.jsonParse(response, "first_install_progress_bar_message", "");
    }

    private void setRelevantUrls(JSONObject response) {
        String env = "";
        try {
            env = app().readFromDisk(EnvironmentUtils.FILE_NAME, EnvironmentUtils.HOST_URL_KEY);
            if (env == null) {
                env = "";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            app.removeFromDisk(EnvironmentUtils.FILE_NAME, EnvironmentUtils.HOST_URL_KEY);
        }
        String parsedPostUrl = Parser.jsonParse(response, "url", "");
        String parsedGetUrl = Parser.jsonParse(response, "get_url", parsedPostUrl);


        setPostUrl(env.isEmpty() ? parsedPostUrl : env);
        setGetUrl(env.isEmpty() ? parsedGetUrl : getPostUrl());


    }

    public String getFirstTimeMessage() {
        return firstTimeMessage;
    }
}
