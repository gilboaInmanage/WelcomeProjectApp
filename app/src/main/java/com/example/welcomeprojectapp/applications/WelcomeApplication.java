package com.example.welcomeprojectapp.applications;

import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.welcomeprojectapp.activities.StartupActivity;
import com.example.welcomeprojectapp.managers.AppManager;
import com.example.welcomeprojectapp.managers.StartUpManager;
import com.google.firebase.FirebaseApp;

import il.co.inmanage.base.BaseApplication;
import il.co.inmanage.managers.BaseAppManager;
import il.co.inmanage.managers.BaseStartUpManager;

public class WelcomeApplication extends BaseApplication {

    private static WelcomeApplication appInstance;
    @Nullable
    @Override
    public String getActivityClassName() {
        return StartupActivity.class.getName();
    }

    @NonNull
    @Override
    public BaseAppManager getAppManager() {
        return null;
    }

    public AppManager appManager(){
        return AppManager.getInstance();
    }
    public StartUpManager startupManager(){
        return StartUpManager.getInstance();
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

    @NonNull
    @Override
    public BaseStartUpManager getStartUpManager() {
        return StartUpManager.getInstance();
    }

    public void onCreate() {
        super.onCreate();

    }
}
