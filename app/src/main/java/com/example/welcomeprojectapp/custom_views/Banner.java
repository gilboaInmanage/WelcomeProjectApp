package com.example.welcomeprojectapp.custom_views;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.welcomeprojectapp.R;
import com.example.welcomeprojectapp.data.BannerData;

import il.co.inmanage.utils.images_fetcher.ImageUtils;
import il.co.inmanage.widgets.InManageTextView;

public class Banner extends RelativeLayout {

    private static final String TAG = "Banner";
    private final Handler durationHandler = new Handler(Looper.getMainLooper());
    private BannerListener bannerListener;
    private final Runnable durationRunnable = new Runnable() {
        @Override
        public void run() {
            if (bannerListener != null) {
                bannerListener.onClose();
            }
        }
    };
    private RelativeLayout rlBanner;
    private ImageView ivBannerClose;
    private InManageTextView tvBannerTitle;
    private InManageTextView tvBannerSubtitle;
    private ImageView ivBannerLogo;

    public Banner(@NonNull Context context) {
        super(context);
        initViews(context);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_banner, this, true);

        rlBanner = findViewById(R.id.rlBanner);
        ivBannerClose = findViewById(R.id.ivBannerClose);
        tvBannerTitle = findViewById(R.id.tvBannerTitle);
        tvBannerSubtitle = findViewById(R.id.tvBannerSubtitle);
        ivBannerLogo = findViewById(R.id.ivBannerLogo);

        ivBannerClose.setOnClickListener(v -> {
            durationHandler.removeCallbacks(durationRunnable);
            if (bannerListener != null) {
                bannerListener.onClose();
            }
        });

    }

    public void setData(BannerData bannerData) {
        Log.d(TAG, "Setting banner data: " + bannerData);
        if (bannerData == null) {
            Log.e(TAG, "BannerData is null");
            return;
        }

        tvBannerTitle.setText(bannerData.getTitle() != null ? bannerData.getTitle() : "");
        Log.d(TAG, "Banner title: " + bannerData.getTitle());

        tvBannerSubtitle.setText(bannerData.getContent() != null ? bannerData.getContent() : "");


        if (bannerData.getContentImage() != null && !bannerData.getContentImage().isEmpty()) {
            ImageUtils.loadImage(bannerData.getContentImage(), ivBannerLogo);
        }

    }


    public void show(long duration) {
        setVisibility(VISIBLE);
        if (duration > 0) {
            durationHandler.postDelayed(durationRunnable, duration);
        }
    }

    public void setBannerListener(BannerListener listener) {
        this.bannerListener = listener;
    }

    public interface BannerListener {
        void onClose();

    }
}
