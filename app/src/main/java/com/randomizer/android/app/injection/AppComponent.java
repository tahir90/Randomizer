package com.randomizer.android.app.injection;

import android.content.Context;

import com.randomizer.android.app.RandomizerApp;
import com.randomizer.android.utils.PreferencesUtils;
import com.randomizer.android.utils.RandomUsersParser;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkApiModule.class})
public interface AppComponent {
    Context getAppContext();

    RandomizerApp getApp();

    Retrofit exposeRetrofit();

    PreferencesUtils exposePreferencesUtils();

    RandomUsersParser exposeParser();
}