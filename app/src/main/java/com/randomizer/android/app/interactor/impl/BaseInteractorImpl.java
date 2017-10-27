package com.randomizer.android.app.interactor.impl;


import android.content.Context;

import com.randomizer.android.app.interactor.BaseInteractor;
import com.randomizer.android.utils.NetworkUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;


public abstract class BaseInteractorImpl implements BaseInteractor {

    public Subscription mSubscription = Subscriptions.empty();

    @Override
    public <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        mSubscription =  observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            Timber.i("mSubscription.unSubscribe()");
            mSubscription.unsubscribe();
        }
    }

    protected boolean isNetworkConnected(Context context) {
        return NetworkUtils.isNetAvailable(context);
    }

}
