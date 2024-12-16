package com.example.welcomeprojectapp.server_responses;

import androidx.annotation.Nullable;

import com.example.welcomeprojectapp.data.BannerData;

import org.json.JSONObject;

import il.co.inmanage.server_responses.BaseServerRequestResponse;

public class FetchBannerResponse extends BaseServerRequestResponse {
    private BannerData bannerData;

    @Override
    protected void parseData(@Nullable JSONObject response) {
        if (response != null) {
            bannerData = (BannerData) new BannerData().createResponse(response);
        }
    }

    public BannerData getBannerData() {
        return bannerData;
    }
}
