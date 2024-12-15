package com.example.welcomeprojectapp.applications;

import android.os.Debug;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.welcomeprojectapp.activities.StartupActivity;
import com.example.welcomeprojectapp.managers.AppManager;
import com.example.welcomeprojectapp.managers.RequestManager;
import com.example.welcomeprojectapp.managers.StartupManager;
import com.example.welcomeprojectapp.server_responses.GeneralDeclarationResponse;

import il.co.inmanage.base.BaseApplication;

public class WelcomeApplication extends BaseApplication {

    private static WelcomeApplication appInstance;

    public void onCreate() {
        super.onCreate();
    }
    @Nullable
    @Override
    public String getActivityClassName() {
        return StartupActivity.class.getName();
    }

    @NonNull
    @Override
    public AppManager getAppManager() {
        return AppManager.getInstance();
    }

    @NonNull
    @Override
    public RequestManager getRequestManager(){
        return RequestManager.getInstance();
    }
    @NonNull
    @Override
    public StartupManager getStartupManager(){
        return StartupManager.getInstance();
    }

    protected void initManagers() {
        super.initManagers();
    }

    public static WelcomeApplication app() {
        if (appInstance == null) {
            appInstance = (WelcomeApplication) BaseApplication.app;
        }
        return appInstance;
    }

    public GeneralDeclarationResponse getGeneralDeclarationResponse() {
        return (GeneralDeclarationResponse) app().getSessionData().getGeneralDeclarationResponse();
    }

}
