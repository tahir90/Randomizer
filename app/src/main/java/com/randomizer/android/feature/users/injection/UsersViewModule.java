package com.randomizer.android.feature.users.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.randomizer.android.app.injection.ActivityScope;
import com.randomizer.android.app.presenter.loader.PresenterFactory;
import com.randomizer.android.feature.users.interactor.UsersInteractor;
import com.randomizer.android.feature.users.interactor.impl.UsersInteractorImpl;
import com.randomizer.android.feature.users.presenter.UsersPresenter;
import com.randomizer.android.feature.users.presenter.impl.UsersPresenterImpl;
import com.randomizer.android.utils.RandomUsersParser;
import com.randomizer.android.webapi.UsersApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class UsersViewModule {
    @Provides
    public UsersInteractor provideInteractor(Context context, UsersApiService apiService, RandomUsersParser parser) {
        return new UsersInteractorImpl(context, apiService, parser);
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

    @ActivityScope
    @Provides
    UsersApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(UsersApiService.class);
    }
}
