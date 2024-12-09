package com.example.welcomeprojectapp.server_responses;

import com.example.welcomeprojectapp.applications.WelcomeApplication;

import il.co.inmanage.parser.Parser;
import il.co.inmanage.server_responses.BaseSetSettingsResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SetSettingsResponse extends BaseSetSettingsResponse {

    private final List<String> getMethods = new ArrayList<>();

    @Override
    public void parseData(JSONObject response) {
        super.parseData(response);

        JSONArray getMethodsArr = Parser.jsonParse(response, "get_methodsArr", new JSONArray());
        for (int i = 0; i < getMethodsArr.length(); i++) {
            String method = Parser.jsonParse(getMethodsArr, i, "");
            if (method != null) {
                getMethods.add(method);
            }
        }
        WelcomeApplication.app().getRequestManager().setMethodsToSendAsGetRequest(getMethods);
    }
}
