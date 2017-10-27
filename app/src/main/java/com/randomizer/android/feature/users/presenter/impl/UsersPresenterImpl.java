package com.randomizer.android.feature.users.presenter.impl;

import android.support.annotation.NonNull;

import com.randomizer.android.app.presenter.impl.BasePresenterImpl;
import com.randomizer.android.constants.AppConstants;
import com.randomizer.android.feature.users.interactor.UsersInteractor;
import com.randomizer.android.feature.users.presenter.UsersPresenter;
import com.randomizer.android.feature.users.view.UsersView;
import com.randomizer.android.model.RandomUser;

import java.util.List;

import javax.inject.Inject;

public final class UsersPresenterImpl extends BasePresenterImpl<UsersView> implements UsersPresenter {
    @NonNull
    private final UsersInteractor mInteractor;
    private boolean mIsLoading;

    @Inject
    public UsersPresenterImpl(@NonNull UsersInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        assert mView != null;
        if (mInteractor.isNetworkConnected()) {
            laodFirstPage();
        } else {
            mView.showNetworkError();
        }
    }

    private void laodFirstPage() {
        fetchNextPage(AppConstants.FIRST_PAGE_NUMBER);
    }


    @Override
    public void onPresenterDestroyed() {
        mInteractor.unsubscribe();

        super.onPresenterDestroyed();
    }


    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void fetchNextPage(int pageNumber) {
        if (!mInteractor.isNetworkConnected()) {
            assert mView != null;
            mView.showNetworkError();
        } else {
            mInteractor.fetchPage(pageNumber, this);
        }
    }


    /**
     * is called when API starts
     */
    @Override
    public void onStart() {
        mIsLoading = true;
        assert mView != null;
        mView.showLoading();
    }

    /**
     * Handles API failure
     * @param message to prompt
     */
    @Override
    public void onFailure(String message) {
        mIsLoading = false;
        assert mView != null;
        mView.showErrorWithMessage(message);
        mView.hideLoading();
    }

    /**
     * Rx-Java onComplete implemetation
     */
    @Override
    public void onComplete() {
        mIsLoading = false;
        assert mView != null;
        mView.hideLoading();
    }

    @Override
    public void onDataResponse(List<RandomUser> list) {
        assert mView != null;
        mView.loadList(list);
    }
}