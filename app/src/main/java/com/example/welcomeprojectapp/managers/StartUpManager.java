package com.example.welcomeprojectapp.managers;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.example.welcomeprojectapp.activities.StartupActivity;
import com.example.welcomeprojectapp.applications.WelcomeApplication;
import com.example.welcomeprojectapp.server_requests.GetHostUrlServerRequest;
import com.example.welcomeprojectapp.server_responses.GetHostUrlResponse;
import com.example.welcomeprojectapp.server_responses.SetSettingsResponse;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.managers.BaseStartUpManager;
import il.co.inmanage.utils.DeviceUtils;
import il.co.inmanage.utils.ScreenUtils;
import il.co.inmanage.server_requests.*;
import il.co.inmanage.server_responses.*;
public class StartUpManager extends BaseStartUpManager {
    private static StartUpManager instance;
    private static final int STEP_VALIDATE_SSL = 10;
    private static final int LAST_STEP = 12;
    private static final String FILENAME = "first_startup";
    private static final String KEY_FIRST_STARTUP = "first_startup";
    @Override
    public int getLastStep() {
        return LAST_STEP;
    }
    public static StartUpManager getInstance() {
        if(instance == null){
            instance = new StartUpManager();
        }
        return instance;
    }

    @Override
    public WelcomeApplication app() {
        return (WelcomeApplication) super.app();
    }
    @Override
    public BaseGetHostUrlResponse createBaseGetHostUrlResponse() {
        return new GetHostUrlResponse();
    }

    @Override
    public BaseSetSettingsResponse createSetSettingsResponse() {
        return new SetSettingsResponse();
    }

    @Override
    public BaseSetSettingsServerRequest getSetSettingsRequest() {
        return new BaseSetSettingsServerRequest(
                app().getLanguageManager().getApplicationLanguage().getTitle(),
                app().getLanguageManager().getApplicationLanguage().getId(),
                ScreenUtils.getDeviceDpiAsString(app()),
                app().getAppVersionName(),
                DeviceUtils.INSTANCE.getVersionOperationSystem(),
                DeviceUtils.getUuid(app()),
                app().getSessionData().getApplicationToken(),
                false,
                getSetSettingsRequestListener()
        );
    }


    @NonNull
    @Override
    public GetHostUrlServerRequest getHostUrlServerRequest() {
        return new GetHostUrlServerRequest(
                new OnServerRequestDoneListener<BaseGetHostUrlServerRequest, BaseGetHostUrlResponse>() {
                    @Override
                    public void onFailure(@NonNull String requestName, @NonNull BaseGetHostUrlServerRequest baseServerRequest, @NonNull BaseServerRequestResponse.ServerRequestFailureResponse serverRequestFailure) {
//                        OnServerRequestDoneListener.super.onFailure(requestName, baseServerRequest, serverRequestFailure);
                    }

                    @Override
                    public void onSuccess(String requestName, BaseGetHostUrlServerRequest baseServerRequest, BaseGetHostUrlResponse response) {
                        onGetHostUrl(response);
                        onRequestSuccess(response);
                    }
                });
    }

    @Override
    public void onGetHostUrl(BaseGetHostUrlResponse response) {
        super.onGetHostUrl(response);
        if (response instanceof GetHostUrlResponse &&
                app().getCurrentActivity() instanceof StartupActivity &&
                isFirstStartup()) {
            ((StartupActivity) app().getCurrentActivity()).setFirstTimeMessage(((GetHostUrlResponse) response).getFirstTimeMessage());
            updateNotFirstStartup();
        }
    }
    private boolean isFirstStartup() {
        return app().getSharedPreferences().readBooleanFromDisk(
                FILENAME,
                KEY_FIRST_STARTUP,
                true
        );
    }

    private void updateNotFirstStartup() {
        app().getSharedPreferences().writeToDisk(FILENAME, KEY_FIRST_STARTUP, false);
    }


    @NonNull
    @Override
    public GeneralDeclarationServerRequest getGeneralDeclarationServerRequest() {
        return null;
    }


}