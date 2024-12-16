package com.example.welcomeprojectapp.managers;

import androidx.annotation.NonNull;

import com.example.welcomeprojectapp.BuildConfig;
import com.example.welcomeprojectapp.applications.WelcomeApplication;

import il.co.inmanage.managers.BaseRequestManager;
import il.co.inmanage.server_requests.BaseServerRequest;
import il.co.inmanage.singleton_holders.SingletonHolder;

public class RequestManager extends BaseRequestManager {

    private static final SingletonHolder<RequestManager> instance =
            new SingletonHolder<>(RequestManager::new);

    public static RequestManager getInstance() {
        return instance.getInstance();
    }

    @NonNull
    @Override
    public String getBaseHostUrl() {
        return BuildConfig.URL;
    }

    @NonNull
    @Override
    public String getBaseUrl() {
        return BuildConfig.URL;
    }

    @Override
    public String getServerVersion() {
        return BuildConfig.SERVER_VERSION;
    }


    @NonNull
    @Override
    public WelcomeApplication app() {
        return (WelcomeApplication) super.app();
    }

    @Override
    public void addToRequestQueue(BaseServerRequest request, String tag) {
        super.addToRequestQueue(request, tag);

    }


}
