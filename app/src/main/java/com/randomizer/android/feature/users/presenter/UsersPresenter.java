package com.randomizer.android.feature.users.presenter;

import com.randomizer.android.app.presenter.BasePresenter;
import com.randomizer.android.feature.users.interactor.UsersInteractor;
import com.randomizer.android.feature.users.view.UsersView;

public interface UsersPresenter extends BasePresenter<UsersView>, UsersInteractor.OnFetchPageListener {

    boolean isLoading();

    void fetchCarousalPage(int nextPageNumber);
}