package com.example.welcomeprojectapp.managers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.welcomeprojectapp.BuildConfig;
import com.example.welcomeprojectapp.R;
import com.example.welcomeprojectapp.applications.WelcomeApplication;

import java.io.File;
import java.io.IOException;

import il.co.inmanage.base.BaseApplication;
import il.co.inmanage.managers.BaseRequestManager;
import il.co.inmanage.server_requests.BaseServerRequest;
import il.co.inmanage.singleton_holders.SingletonHolder;
import il.co.inmanage.utils.FileUtils;

public class RequestManager extends BaseRequestManager {

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 10023;
    // public String baseUrl ="";

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
