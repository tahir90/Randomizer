package com.randomizer.android.feature.splash;

import android.support.annotation.UiThread;

import com.randomizer.android.app.view.BaseView;

@UiThread
public interface SplashView extends BaseView {

    void showLoading();

    void hideLoading();

    void finishActivity();

    void launchNextActivity();

    void showNetworkError();

}