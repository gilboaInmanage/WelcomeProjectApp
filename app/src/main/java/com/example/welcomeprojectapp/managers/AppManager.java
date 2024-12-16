package com.example.welcomeprojectapp.managers;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.welcomeprojectapp.applications.WelcomeApplication;

import il.co.inmanage.managers.BaseAppManager;
import il.co.inmanage.managers.BaseProcessManager;
import il.co.inmanage.singleton_holders.SingletonHolder;

public class AppManager extends BaseAppManager {
    private static final SingletonHolder<AppManager> instance =
            new SingletonHolder<>(AppManager::new);

    public static AppManager getInstance() {
        return instance.getInstance();
    }

    @Nullable
    @Override
    protected BaseProcessManager startOtherProcess(int processIndex, @Nullable Bundle bundle, @Nullable OnProcessStepChangedListener onProcessStepChangedListener) {
        return app().getStartupManager();

    }

    public WelcomeApplication app() {
        return (WelcomeApplication) super.app();
    }


}
