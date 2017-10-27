package com.randomizer.android.feature.users.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.randomizer.android.R;
import com.randomizer.android.app.injection.AppComponent;
import com.randomizer.android.app.presenter.loader.PresenterFactory;
import com.randomizer.android.app.view.impl.BaseActivity;
import com.randomizer.android.feature.users.injection.DaggerUsersViewComponent;
import com.randomizer.android.feature.users.injection.UsersViewModule;
import com.randomizer.android.feature.users.presenter.UsersPresenter;
import com.randomizer.android.feature.users.view.UsersView;

import javax.inject.Inject;

public final class UsersActivity extends BaseActivity<UsersPresenter, UsersView> implements UsersView {
    @Inject
    PresenterFactory<UsersPresenter> mPresenterFactory;

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerUsersViewComponent.builder()
                .appComponent(parentComponent)
                .usersViewModule(new UsersViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<UsersPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_users;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

    }
}
