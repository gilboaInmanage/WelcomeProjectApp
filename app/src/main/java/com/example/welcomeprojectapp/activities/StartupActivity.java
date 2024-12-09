package com.example.welcomeprojectapp.activities;
import il.co.inmanage.widgets.InManageTextView;
import com.airbnb.lottie.LottieAnimationView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.example.welcomeprojectapp.R;
import com.example.welcomeprojectapp.applications.WelcomeApplication;

import java.util.Random;

import il.co.inmanage.activities.BaseStartUpActivity;

public class StartupActivity extends BaseStartUpActivity {
    private InManageTextView tvProgress, tvStatus, tvFirstStartup, tvVersionName;
    private LottieAnimationView splashAnimation;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }

    @Override
    public void onStartupProcessStepChanged(int currentStep, boolean isFinished) {
        super.onStartupProcessStepChanged(currentStep, isFinished);
        // Updating progress views
        float lastStep = (float) app().getStartUpManager().getLastStep();
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
        tvStatus = findViewById(R.id.tvStatus);
        progressBar = findViewById(R.id.severConnectionProgressBar);
        tvProgress = findViewById(R.id.tvProgress);
        tvVersionName = findViewById(R.id.tvVersionName);
        tvFirstStartup = findViewById(R.id.tvFirstStartup);
        splashAnimation = findViewById(R.id.splashAnimation);
        //initBackgroundSplash();
        // initTexts();

    }

    private void initBackgroundSplash() {
        splashAnimation.setAnimation(R.raw.loading_lottie);
    }


    @NonNull
    @Override
    public WelcomeApplication app(){
        return (WelcomeApplication) super.app();
    }

//    private void initTexts() {
//        tvVersionName.setText(app().getAppVersionName());
//        String translation = app().getAppManager().getTranslations().getTranslation("mobile_splash_updating_menu");
//        if (!translation.isEmpty()) {
//            tvStatus.setText(translation);
//        }
//    }
}
