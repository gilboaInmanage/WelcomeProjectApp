package com.example.welcomeprojectapp.server_requests;

import com.example.welcomeprojectapp.server_responses.GetHostUrlResponse;

import org.json.JSONObject;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.server_requests.BaseGetHostUrlServerRequest;
import il.co.inmanage.server_requests.RequestOptions;
import il.co.inmanage.server_responses.BaseGetHostUrlResponse;
import il.co.inmanage.server_responses.BaseServerRequestResponse;

public class GetHostUrlServerRequest extends BaseGetHostUrlServerRequest {

    public GetHostUrlServerRequest(OnServerRequestDoneListener<BaseGetHostUrlServerRequest, BaseGetHostUrlResponse> listener) {
        super(new RequestOptions(), listener);

    }

    @Override
    public BaseServerRequestResponse buildResponse(JSONObject jsonObject) {
        return new GetHostUrlResponse().createResponse(jsonObject);
    }
}