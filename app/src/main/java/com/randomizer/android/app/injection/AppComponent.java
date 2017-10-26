package com.randomizer.android.app.injection;

import android.content.Context;

import com.randomizer.android.app.RandomizerApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkApiModule.class})
public interface AppComponent {
    Context getAppContext();

    RandomizerApp getApp();
}