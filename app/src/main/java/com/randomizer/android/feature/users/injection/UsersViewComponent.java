package com.randomizer.android.feature.users.injection;

import com.randomizer.android.app.injection.ActivityScope;
import com.randomizer.android.app.injection.AppComponent;
import com.randomizer.android.feature.users.view.impl.UsersActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = UsersViewModule.class)
public interface UsersViewComponent {
    void inject(UsersActivity activity);
}