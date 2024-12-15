package com.example.welcomeprojectapp.managers;

import static il.co.inmanage.utils.LoggingHelper.d;

import android.os.Debug;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.welcomeprojectapp.activities.StartupActivity;
import com.example.welcomeprojectapp.applications.WelcomeApplication;
import com.example.welcomeprojectapp.server_requests.GetHostUrlServerRequest;
import com.example.welcomeprojectapp.server_responses.GeneralDeclarationResponse;
import com.example.welcomeprojectapp.server_responses.GetHostUrlResponse;
import com.example.welcomeprojectapp.server_responses.SetSettingsResponse;

import java.util.Objects;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.managers.BaseStartUpManager;
import il.co.inmanage.singleton_holders.SingletonHolder;
import il.co.inmanage.utils.DeviceUtils;
import il.co.inmanage.utils.ScreenUtils;
import il.co.inmanage.server_requests.*;
import il.co.inmanage.server_responses.*;
public class StartupManager extends BaseStartUpManager {
    private static final int STEP_VALIDATE_SSL = 10;
    private static final int LAST_STEP = 12;
    private static final String FILENAME = "first_startup";
    private static final String KEY_FIRST_STARTUP = "first_startup";


    private static final SingletonHolder<StartupManager> instance =
            new SingletonHolder<>(StartupManager::new);

    @Override
    public int getLastStep() {
        return LAST_STEP;
    }

    public static StartupManager getInstance() {
        return instance.getInstance();
    }

    @NonNull
    @Override
    public WelcomeApplication app() {
        return (WelcomeApplication) super.app();
    }
    @Override
    public GetHostUrlResponse createBaseGetHostUrlResponse() {
        return new GetHostUrlResponse();
    }

    @Override
    public BaseSetSettingsResponse createSetSettingsResponse() {
        return new SetSettingsResponse();
    }

    @NonNull
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
        return new GetHostUrlServerRequest(new OnServerRequestDoneListener<BaseGetHostUrlServerRequest, BaseGetHostUrlResponse>() {
            @Override
            public void onSuccess(@NonNull String requestName, @NonNull BaseGetHostUrlServerRequest baseServerRequest, @NonNull BaseGetHostUrlResponse baseResponse) {
                Log.d("GetHostUrl", "Request succeeded: " + baseResponse.getPostUrl());
                onGetHostUrl(baseResponse);
                onRequestSuccess(baseResponse);
            }

            @Override
            public void onFailure(@NonNull String requestName, @NonNull BaseGetHostUrlServerRequest baseServerRequest, @NonNull BaseServerRequestResponse.ServerRequestFailureResponse failureResponse) {
                Log.e("GetHostUrl", "Request failed: " + failureResponse.toString());
            }
        });
    }

    @Override
    public void onGetHostUrl(@NonNull BaseGetHostUrlResponse baseResponse) {
//        Debug.waitForDebugger();
        d("onGetHostUrl1", baseResponse.getPostUrl());
        super.onGetHostUrl(baseResponse);
        d("onGetHostUrl2", baseResponse.getPostUrl());

        if (baseResponse instanceof GetHostUrlResponse && app().getCurrentActivity() instanceof StartupActivity && isFirstStartup()) {
            ((StartupActivity)(app().getCurrentActivity())).setFirstTimeMessage(((GetHostUrlResponse) baseResponse).getFirstTimeMessage());
            updateNotFirstStartup();
        }

    }

    private boolean isFirstStartup() {
        return app().getSharedPreferences() != null &&
                app().getSharedPreferences().readBooleanFromDisk(
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
        return new GeneralDeclarationServerRequest(new OnServerRequestDoneListener<GeneralDeclarationServerRequest, GeneralDeclarationResponse>() {

            @Override
            public void onSuccess(@NonNull String requestName, @NonNull GeneralDeclarationServerRequest baseServerRequest, @NonNull GeneralDeclarationResponse baseResponse) {
                app().getSessionData().setGeneralDeclarationResponse(baseResponse);
                app().getAppManager().onGeneralDeclarationResponse(baseResponse);
                onRequestSuccess(baseResponse);
            }

            @Override
            public void onFailure(@NonNull String requestName, @NonNull GeneralDeclarationServerRequest baseServerRequest, @NonNull BaseServerRequestResponse.ServerRequestFailureResponse serverRequestFailure) {
                // Handle failure
            }
        });
    }



}
