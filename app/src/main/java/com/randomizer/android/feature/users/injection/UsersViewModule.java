package com.randomizer.android.feature.users.injection;

import android.support.annotation.NonNull;

import com.randomizer.android.app.presenter.loader.PresenterFactory;
import com.randomizer.android.feature.users.interactor.UsersInteractor;
import com.randomizer.android.feature.users.interactor.impl.UsersInteractorImpl;
import com.randomizer.android.feature.users.presenter.UsersPresenter;
import com.randomizer.android.feature.users.presenter.impl.UsersPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public final class UsersViewModule {
    @Provides
    public UsersInteractor provideInteractor() {
        return new UsersInteractorImpl();
    }

    @Provides
    public PresenterFactory<UsersPresenter> providePresenterFactory(@NonNull final UsersInteractor interactor) {
        return new PresenterFactory<UsersPresenter>() {
            @NonNull
            @Override
            public UsersPresenter create() {
                return new UsersPresenterImpl(interactor);
            }
        };
    }
}
