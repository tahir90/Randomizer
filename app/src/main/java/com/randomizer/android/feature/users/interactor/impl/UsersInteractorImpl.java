package com.randomizer.android.feature.users.interactor.impl;

import android.content.Context;

import com.randomizer.android.app.interactor.impl.BaseInteractorImpl;
import com.randomizer.android.feature.users.interactor.UsersInteractor;
import com.randomizer.android.model.RandomUser;
import com.randomizer.android.model.response.RandomUsersListResponse;
import com.randomizer.android.utils.RandomUsersParser;
import com.randomizer.android.webapi.UsersApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public final class UsersInteractorImpl extends BaseInteractorImpl implements UsersInteractor {

    private final Context mContext;
    private final UsersApiService mApiService;
    private final RandomUsersParser mParser;

    @Inject
    public UsersInteractorImpl(Context context, UsersApiService apiService, RandomUsersParser parser) {

        mContext = context;
        mApiService = apiService;
        mParser = parser;
    }

    @Override
    public boolean isNetworkConnected() {
        return super.isNetworkConnected(mContext);
    }

    @Override
    public void fetchPage(int pageNumber, final OnFetchPageListener listener) {
        listener.onStart();

        Observable<RandomUsersListResponse> observable = mApiService.getUsers(String.valueOf(pageNumber));

        subscribe(observable, new Observer<RandomUsersListResponse>() {

            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e.getMessage());
                listener.onComplete();
            }

            @Override
            public void onNext(RandomUsersListResponse response) {
                List<RandomUser> randomUsers = mParser.parseUsers(response);
                listener.onDataResponse(randomUsers);
            }
        });
    }
}