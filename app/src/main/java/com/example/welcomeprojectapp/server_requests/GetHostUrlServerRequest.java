package com.example.welcomeprojectapp.server_requests;

import androidx.annotation.NonNull;

import com.example.welcomeprojectapp.server_responses.GetHostUrlResponse;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.server_requests.BaseGetHostUrlServerRequest;
import il.co.inmanage.server_responses.BaseGetHostUrlResponse;
import il.co.inmanage.server_responses.BaseServerRequestResponse;
import il.co.inmanage.server_requests.RequestOptions;

import org.json.JSONObject;

public class GetHostUrlServerRequest extends BaseGetHostUrlServerRequest {

    public GetHostUrlServerRequest(OnServerRequestDoneListener<BaseGetHostUrlServerRequest, BaseGetHostUrlResponse> listener) {
        super(new RequestOptions(false,false,true,true),listener);
    }

    @NonNull
    @Override
    public BaseServerRequestResponse buildResponse(JSONObject jsonObject) {
        return new GetHostUrlResponse().createResponse(jsonObject);
    }
}