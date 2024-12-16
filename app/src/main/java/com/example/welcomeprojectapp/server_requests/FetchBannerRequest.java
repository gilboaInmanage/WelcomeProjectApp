package com.example.welcomeprojectapp.server_requests;

import com.example.welcomeprojectapp.server_responses.FetchBannerResponse;

import org.json.JSONObject;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.server_requests.BaseServerRequest;
import il.co.inmanage.server_responses.BaseServerRequestResponse;

public class FetchBannerRequest extends BaseServerRequest {

    public FetchBannerRequest(OnServerRequestDoneListener<FetchBannerRequest, FetchBannerResponse> listener) {
        super("FetchBanner", null, listener);
    }

    @Override
    public BaseServerRequestResponse buildResponse(JSONObject jsonObject) {
        return new FetchBannerResponse().createResponse(jsonObject);
    }
}
