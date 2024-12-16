package com.example.welcomeprojectapp.custom_views;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
    private static final String DEFAULT_COLOR = "#FFFFFF"; // Default fallback color

    private final Handler durationHandler = new Handler(Looper.getMainLooper());
    private final Runnable durationRunnable = new Runnable() {
        @Override
        public void run() {
            if (bannerListener != null) {
                bannerListener.onClose();
            }
        }
    };

    private BannerListener bannerListener;

    private RelativeLayout rlBanner;
    private ImageView ivBannerBackground;
    private ImageView ivBannerClose;
    private ImageView ivBannerIcon;
    private InManageTextView tvBannerTitle;
    private InManageTextView tvBannerSubtitle;
    private ImageView ivBannerLogo;
    private LinearLayout llBannerBtn;
    private InManageTextView tvBannerBtn;

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
        ivBannerBackground = findViewById(R.id.ivBannerBackground);
        ivBannerClose = findViewById(R.id.ivBannerClose);
        ivBannerIcon = findViewById(R.id.ivBannerIcon);
        tvBannerTitle = findViewById(R.id.tvBannerTitle);
        tvBannerSubtitle = findViewById(R.id.tvBannerSubtitle);
        ivBannerLogo = findViewById(R.id.ivBannerLogo);
        llBannerBtn = findViewById(R.id.llBannerBtn);
        tvBannerBtn = findViewById(R.id.tvBannerBtn);

        ivBannerClose.setOnClickListener(v -> {
            durationHandler.removeCallbacks(durationRunnable);
            if (bannerListener != null) {
                bannerListener.onClose();
            }
        });

        llBannerBtn.setOnClickListener(v -> {
            durationHandler.removeCallbacks(durationRunnable);
            if (bannerListener != null) {
                bannerListener.onBtnClick();
            }
        });
    }

    public void setData(BannerData bannerData) {
        Log.d(TAG, "Setting banner data: " + bannerData);
        if (bannerData == null) {
            Log.e(TAG, "BannerData is null");
            return;
        }

        // Set title and its color
        if (tvBannerTitle != null) {
            tvBannerTitle.setText(bannerData.getTitle() != null ? bannerData.getTitle() : "");
            tvBannerTitle.setTextColor(getSafeColor(bannerData.getTitleColor(), DEFAULT_COLOR));
        }

        // Set subtitle and its color
        if (tvBannerSubtitle != null) {
            tvBannerSubtitle.setText(Html.fromHtml(
                    bannerData.getContent() != null ? bannerData.getContent() : "",
                    Html.FROM_HTML_MODE_COMPACT));
            tvBannerSubtitle.setTextColor(getSafeColor(bannerData.getContentColor(), DEFAULT_COLOR));
        }

        // Set button content, color, and background
        if (tvBannerBtn != null) {
            boolean hasButtonContent = bannerData.getButtonContent() != null && !bannerData.getButtonContent().isEmpty();
            tvBannerBtn.setText(hasButtonContent ? bannerData.getButtonContent() : "");
            tvBannerBtn.setTextColor(getSafeColor(bannerData.getButtonContentColor(), DEFAULT_COLOR));
            tvBannerBtn.setBackgroundColor(getSafeColor(bannerData.getButtonBackgroundColor(), "#FF9800"));
            tvBannerBtn.setVisibility(hasButtonContent ? VISIBLE : GONE);
        }

        // Set banner logo image
        if (bannerData.getContentImage() != null && !bannerData.getContentImage().isEmpty()) {
            ImageUtils.loadImage(bannerData.getContentImage(), ivBannerLogo);
            ivBannerLogo.setVisibility(VISIBLE);
        } else {
            ivBannerLogo.setVisibility(GONE);
        }

        // Set banner background image or color
        if (bannerData.getBackgroundImage() != null && bannerData.getBackgroundType().equals(BannerData.BACKGROUND_TYPE_IMAGE)) {
            ImageUtils.loadImage(bannerData.getBackgroundImage(), ivBannerBackground);
        } else if (bannerData.getBackgroundColor() != null && !bannerData.getBackgroundColor().isEmpty()) {
            rlBanner.setBackgroundColor(getSafeColor(bannerData.getBackgroundColor(), "#000000"));
        }
    }

    private int getSafeColor(String colorString, String fallbackColor) {
        try {
            if (colorString != null && !colorString.isEmpty()) {
                return Color.parseColor(colorString);
            }
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Invalid color format: " + colorString, e);
        }
        return Color.parseColor(fallbackColor);
    }

    public void show(long duration) {
        setVisibility(VISIBLE);
        if (duration > 0) {
            durationHandler.postDelayed(durationRunnable, duration);
        }
    }

    public interface BannerListener {
        void onClose();

        void onBtnClick();
    }

    public void setBannerListener(BannerListener listener) {
        this.bannerListener = listener;
    }
}
