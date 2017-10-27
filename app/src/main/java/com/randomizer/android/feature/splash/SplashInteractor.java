package com.randomizer.android.feature.splash;


import com.randomizer.android.app.interactor.BaseInteractor;

public interface SplashInteractor extends BaseInteractor {
    
    boolean isSplashDone();

    void setSpalshDone();
}