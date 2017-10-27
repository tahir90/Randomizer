package com.randomizer.android.feature.users.interactor;

import com.randomizer.android.app.interactor.BaseInteractor;
import com.randomizer.android.model.RandomUser;

import java.util.List;

public interface UsersInteractor extends BaseInteractor {

    void fetchPage(int pageNumber, OnFetchPageListener usersPresenter);

    interface OnFetchPageListener {

        void onStart();

        void onFailure(String message);

        void onComplete();

        void onDataResponse(List<RandomUser> list);
    }
}