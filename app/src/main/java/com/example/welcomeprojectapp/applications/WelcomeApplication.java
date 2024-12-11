package com.example.welcomeprojectapp.applications;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.welcomeprojectapp.activities.StartupActivity;
import com.example.welcomeprojectapp.managers.AppManager;
import com.example.welcomeprojectapp.managers.RequestManager;
import com.example.welcomeprojectapp.managers.StartupManager;

import il.co.inmanage.base.BaseApplication;

public class WelcomeApplication extends BaseApplication {

    private static WelcomeApplication appInstance;
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


    public void onCreate() {
        super.onCreate();
        /// Initialize StartUpManager
//        StartUpManager startupManager = StartUpManager.getInstance();
//        startupManager.onActivityCreate(this.getCurrentActivity()); // Ensure proper initialization
//        Log.d("StartUpManager", "StartUpManager instance: " + startupManager);
//
//        // Ensure AppManager is initialized
//        AppManager appManager = AppManager.getInstance();
//        Log.d("AppManager", "AppManager instance: " + appManager);
    }


}
