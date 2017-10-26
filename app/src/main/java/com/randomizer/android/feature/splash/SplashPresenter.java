package com.randomizer.android.feature.splash;

import com.randomizer.android.app.presenter.BasePresenter;

public interface SplashPresenter extends BasePresenter<SplashView> {

    void startLoading();

    void stopLoading();

    void doSplash();

    void checkNetwork();

    void launchNextActivity();
}