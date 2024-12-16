package com.example.welcomeprojectapp.server_requests;

import com.example.welcomeprojectapp.server_responses.GeneralDeclarationResponse;

import org.json.JSONObject;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.server_requests.GeneralDeclarationServerRequest;
import il.co.inmanage.server_responses.BaseServerRequestResponse;


public class CustomGeneralDeclarationServerRequest extends GeneralDeclarationServerRequest {

    public CustomGeneralDeclarationServerRequest(OnServerRequestDoneListener<GeneralDeclarationServerRequest, GeneralDeclarationResponse> onServerRequestDone) {
        super(onServerRequestDone);
    }

    @Override
    public BaseServerRequestResponse buildResponse(JSONObject jsonObject) {
        return new GeneralDeclarationResponse().createResponse(jsonObject);
    }
}