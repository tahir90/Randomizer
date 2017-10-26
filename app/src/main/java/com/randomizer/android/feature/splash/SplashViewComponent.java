package com.randomizer.android.feature.splash;

import com.randomizer.android.app.injection.ActivityScope;
import com.randomizer.android.app.injection.AppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = SplashViewModule.class)
public interface SplashViewComponent {
    void inject(SplashActivity activity);
}