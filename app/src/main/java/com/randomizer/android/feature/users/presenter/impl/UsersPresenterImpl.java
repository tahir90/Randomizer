package com.randomizer.android.feature.users.presenter.impl;

import android.support.annotation.NonNull;

import com.randomizer.android.app.presenter.impl.BasePresenterImpl;
import com.randomizer.android.feature.users.interactor.UsersInteractor;
import com.randomizer.android.feature.users.presenter.UsersPresenter;
import com.randomizer.android.feature.users.view.UsersView;

import javax.inject.Inject;

public final class UsersPresenterImpl extends BasePresenterImpl<UsersView> implements UsersPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final UsersInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public UsersPresenterImpl(@NonNull UsersInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}