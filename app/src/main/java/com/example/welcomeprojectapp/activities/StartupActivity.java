package com.example.welcomeprojectapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.example.welcomeprojectapp.R;
import com.example.welcomeprojectapp.applications.WelcomeApplication;
import com.example.welcomeprojectapp.custom_views.Banner;
import com.example.welcomeprojectapp.data.BannerData;

import il.co.inmanage.activities.BaseStartUpActivity;
import il.co.inmanage.widgets.InManageTextView;

public class StartupActivity extends BaseStartUpActivity {
    private final int BANNER_DURATION_MS = 5000; // Banner duration: 5 seconds
    private InManageTextView tvProgress, tvFirstStartup;
    private LottieAnimationView splashAnimation;
    private ProgressBar progressBar;
    private Banner bannerView;

    protected void onCreate(Bundle savedInstanceState) {
        //Debug.waitForDebugger();
        super.onCreate(savedInstanceState);
        initViews();
    }


    @Override
    protected int getContentResourceId() {
        return R.layout.activity_startup;
    }

    @Override
    protected void startNextProcess() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showBannerScreen();
            }
        }, 200);
    }

    @Override
    public void onStartupProcessStepChanged(int currentStep, boolean isFinished) {
        super.onStartupProcessStepChanged(currentStep, isFinished);

        // Updating progress views
        float lastStep = (float) app().getStartupManager().getLastStep();
        int progress = (int) ((currentStep / lastStep) * 100f);
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
        if (tvProgress != null) {
            tvProgress.setText(progress + "%");
        }
    }

    public void setFirstTimeMessage(String message) {
        if (tvFirstStartup != null) {
            tvFirstStartup.setVisibility(View.VISIBLE);
            tvFirstStartup.setText(message);
        }
    }

    public void initViews() {
        super.initViews();
        progressBar = findViewById(R.id.severConnectionProgressBar);
        tvProgress = findViewById(R.id.tvProgress);
        tvFirstStartup = findViewById(R.id.tvFirstStartup);
        splashAnimation = findViewById(R.id.splashAnimation);
        splashAnimation.setAnimation(R.raw.loading_lottie);
        bannerView = findViewById(R.id.bannerView);
        splashAnimation.playAnimation();

    }

    @NonNull
    @Override
    public WelcomeApplication app() {
        return (WelcomeApplication) super.app();
    }

    private void showBannerScreen() {
        splashAnimation.setVisibility(View.GONE);
        bannerView.setVisibility(View.VISIBLE);
    }

    public void showBanner(BannerData bannerData) {
        if (bannerData == null || !bannerData.isShouldShowBanner()) {
            return;
        }
        Log.d("Banner", "Showing banner: " + bannerData);
        bannerView.setData(bannerData);
        bannerView.setBannerListener(new Banner.BannerListener() {
            @Override
            public void onClose() {
                goToMainActivity();
            }
        });
        bannerView.show(BANNER_DURATION_MS);
    }

    private void goToMainActivity() {
        app().getAppManager().startMainActivity(MainActivity.class.getName());
    }


}
