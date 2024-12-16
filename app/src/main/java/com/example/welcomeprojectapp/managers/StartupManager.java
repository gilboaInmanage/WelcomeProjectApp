package com.example.welcomeprojectapp.managers;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.welcomeprojectapp.activities.StartupActivity;
import com.example.welcomeprojectapp.applications.WelcomeApplication;
import com.example.welcomeprojectapp.data.BannerData;
import com.example.welcomeprojectapp.server_requests.CustomGeneralDeclarationServerRequest;
import com.example.welcomeprojectapp.server_requests.GetHostUrlServerRequest;
import com.example.welcomeprojectapp.server_responses.GeneralDeclarationResponse;
import com.example.welcomeprojectapp.server_responses.GetHostUrlResponse;
import com.example.welcomeprojectapp.server_responses.SetSettingsResponse;

import il.co.inmanage.interfaces.OnServerRequestDoneListener;
import il.co.inmanage.managers.BaseStartUpManager;
import il.co.inmanage.server_requests.BaseGetHostUrlServerRequest;
import il.co.inmanage.server_requests.BaseSetSettingsServerRequest;
import il.co.inmanage.server_requests.GeneralDeclarationServerRequest;
import il.co.inmanage.server_responses.BaseGetHostUrlResponse;
import il.co.inmanage.server_responses.BaseServerRequestResponse;
import il.co.inmanage.server_responses.BaseSetSettingsResponse;
import il.co.inmanage.singleton_holders.SingletonHolder;
import il.co.inmanage.utils.DeviceUtils;
import il.co.inmanage.utils.ScreenUtils;

public class StartupManager extends BaseStartUpManager {
    private static final int LAST_STEP = 10;
    private static final String FILENAME = "first_startup";
    private static final String KEY_FIRST_STARTUP = "first_startup";

    private static final SingletonHolder<StartupManager> instance = new SingletonHolder<>(StartupManager::new);

    public static StartupManager getInstance() {
        return instance.getInstance();
    }

    @NonNull
    @Override
    public WelcomeApplication app() {
        return (WelcomeApplication) super.app();
    }

    @Override
    public int getLastStep() {
        return LAST_STEP;
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
        super.onGetHostUrl(baseResponse);

        if (baseResponse instanceof GetHostUrlResponse &&
                app().getCurrentActivity() instanceof StartupActivity &&
                isFirstStartup()) {
            ((StartupActivity) app().getCurrentActivity()).setFirstTimeMessage(
                    ((GetHostUrlResponse) baseResponse).getFirstTimeMessage()
            );
            updateNotFirstStartup();
        }
    }

    private boolean isFirstStartup() {
        return app().getSharedPreferences() != null &&
                app().getSharedPreferences().readBooleanFromDisk(FILENAME, KEY_FIRST_STARTUP, true);
    }

    private void updateNotFirstStartup() {
        app().getSharedPreferences().writeToDisk(FILENAME, KEY_FIRST_STARTUP, false);
    }

    @NonNull
    @Override
    public GeneralDeclarationServerRequest getGeneralDeclarationServerRequest() {
        return new CustomGeneralDeclarationServerRequest(new OnServerRequestDoneListener<GeneralDeclarationServerRequest, GeneralDeclarationResponse>() {
            @Override
            public void onSuccess(@NonNull String requestName, @NonNull GeneralDeclarationServerRequest baseServerRequest, @NonNull GeneralDeclarationResponse baseResponse) {
                app().getSessionData().setGeneralDeclarationResponse(baseResponse);
                app().getAppManager().onGeneralDeclarationResponse(baseResponse);
                onRequestSuccess(baseResponse);
                Log.d("GeneralDeclaration1", "Request succeeded: " + baseResponse);

                Log.d("GeneralDeclaration", "Request succeeded: " + app().getSessionData().getGeneralDeclarationResponse());
            }

            @Override
            public void onFailure(@NonNull String requestName, @NonNull GeneralDeclarationServerRequest baseServerRequest, @NonNull BaseServerRequestResponse.ServerRequestFailureResponse serverRequestFailure) {
                Log.e("GeneralDeclaration", "Request failed: " + serverRequestFailure.toString());
            }
        });
    }

    private void showTransitionBanner() {

        BannerData  bannerData = new BannerData();
        bannerData.setTitle("Welcome to McDonald's!");
        bannerData.setContent("Enjoy the best meals in town.");
        bannerData.setContentImage("https://upload.wikimedia.org/wikipedia/en/2/26/We_Are_Your_Friends.jpg");
        bannerData.setButtonContent("Explore Menu");
        bannerData.setButtonUrl("https://www.mcdonalds.co.il");
        bannerData.setShouldShowBanner(true);
        Log.d("Banner", "Showing banner: " + bannerData);
        // Show banner if activity is StartupActivity
        if (app().getCurrentActivity() instanceof StartupActivity) {
            ((StartupActivity) app().getCurrentActivity()).showBanner(bannerData);
        } else {
            navigateToMainActivity();
        }
    }


    private void navigateToMainActivity() {
        app().getAppManager().startMainActivity(com.example.welcomeprojectapp.activities.MainActivity.class.getName());
    }

    @Override
    public void startNextProcess() {
        Log.d("StartupManager", "Starting next process...");
        new Handler().postDelayed(() -> {
            if (app().getCurrentActivity() instanceof StartupActivity) {
                showTransitionBanner();
            } else {
                Log.e("StartupManager", "Current activity is not StartupActivity. Navigating to MainActivity.");
                navigateToMainActivity();
            }
        }, 200);    }
}
