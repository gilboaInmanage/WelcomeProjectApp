package com.example.welcomeprojectapp.server_responses;

import com.example.welcomeprojectapp.applications.WelcomeApplication;
import com.example.welcomeprojectapp.utils.EnvironmentUtils;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseGetHostUrlResponse;
import org.json.JSONObject;

public class GetHostUrlResponse extends BaseGetHostUrlResponse {

    private String firstTimeMessage;

    @Override
    public void parseData(JSONObject response) {
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
            env = WelcomeApplication.app().readFromDisk(EnvironmentUtils.FILE_NAME, EnvironmentUtils.HOST_URL_KEY);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            WelcomeApplication.app().removeFromDisk(EnvironmentUtils.FILE_NAME, EnvironmentUtils.HOST_URL_KEY);
        }

        // Explicitly check if env is null or empty
        if (env == null || env.isEmpty()) {
            setPostUrl(Parser.jsonParse(response, "url", ""));
            setGetUrl(Parser.jsonParse(response, "get_url", getPostUrl()));
        } else {
            setPostUrl(env);
            setGetUrl(env);
        }
    }
    public String getFirstTimeMessage() {
        return firstTimeMessage;
    }
}
