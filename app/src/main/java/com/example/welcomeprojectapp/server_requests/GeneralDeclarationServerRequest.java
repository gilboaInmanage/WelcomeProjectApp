package com.example.welcomeprojectapp.server_requests;

import android.util.Log;

import com.example.welcomeprojectapp.server_responses.GeneralDeclarationResponse;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.server_responses.BaseServerRequestResponse;

import org.json.JSONObject;

public class GeneralDeclarationServerRequest  extends il.co.inmanage.server_requests.GeneralDeclarationServerRequest {
    public GeneralDeclarationServerRequest(
            OnServerRequestDoneListener<com.example.welcomeprojectapp.server_requests.GeneralDeclarationServerRequest, GeneralDeclarationResponse> onServerRequestDone
            ) {
        super(onServerRequestDone);
    }

    @Override
    public BaseServerRequestResponse buildResponse(JSONObject jsonObject) {
        return new GeneralDeclarationResponse().createResponse(jsonObject);
    }
}