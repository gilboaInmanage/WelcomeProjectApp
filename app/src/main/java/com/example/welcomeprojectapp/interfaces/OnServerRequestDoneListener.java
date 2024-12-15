package com.example.welcomeprojectapp.interfaces;

public interface OnServerRequestDoneListener {
    default void onSuccess(String requestName, Object responseObject) {
        // Default implementation
    }
}
