package com.example.welcomeprojectapp.managers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.welcomeprojectapp.BuildConfig;
import com.example.welcomeprojectapp.applications.WelcomeApplication;

import java.io.File;
import java.io.IOException;

import il.co.inmanage.base.BaseApplication;
import il.co.inmanage.managers.BaseRequestManager;
import il.co.inmanage.singleton_holders.SingletonHolder;
import il.co.inmanage.utils.FileUtils;

public class RequestManager extends BaseRequestManager {

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 10023;

    private static final SingletonHolder<RequestManager> instance =
    new SingletonHolder<>(RequestManager::new);
    private String baseUrl = "";
    public static RequestManager getInstance() {
        return instance.getInstance();
    }


    @NonNull
    @Override
    public String getBaseUrl() {
        return baseUrl.isEmpty() ? BuildConfig.URL : baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public WelcomeApplication app() {
        return (WelcomeApplication) super.app();
    }

    private void showApiMethodToast(String apiMethod) {
        if (app().getCurrentActivity() != null) {
            app().getCurrentActivity().runOnUiThread(() ->
            Toast.makeText(app(), apiMethod, Toast.LENGTH_SHORT).show()
            );
        }
    }

//    private File createFile() {
//        File file = null;
//        try {
//            file = FileUtils.createFileLog(app(), "logs.txt", apiCallsLog);
//        } catch (IOException e) {
//            FileUtils.saveException(app(), e, "ErrorCreateLogsFile");
//        }
//        return file;
//    }
}
