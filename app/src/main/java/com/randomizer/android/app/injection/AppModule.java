package com.randomizer.android.app.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.randomizer.android.app.RandomizerApp;

import dagger.Module;
import dagger.Provides;

@Module
public final class AppModule {
    @NonNull
    private final RandomizerApp mApp;

    public AppModule(@NonNull RandomizerApp app) {
        mApp = app;
    }

    @Provides
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    public RandomizerApp provideApp() {
        return mApp;
    }
}
