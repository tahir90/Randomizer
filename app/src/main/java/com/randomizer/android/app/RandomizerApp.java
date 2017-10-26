package com.randomizer.android.app;

import android.app.Application;
import android.support.annotation.NonNull;

import com.randomizer.android.BuildConfig;
import com.randomizer.android.app.injection.AppComponent;
import com.randomizer.android.app.injection.AppModule;
import com.randomizer.android.app.injection.DaggerAppComponent;
import com.randomizer.android.app.injection.NetworkApiModule;
import com.randomizer.android.constants.AppConstants;

import timber.log.Timber;


public final class RandomizerApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkApiModule(new NetworkApiModule(AppConstants.RANDOM_USER_API_URL))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}