package com.randomizer.android.feature.splash;

import android.content.Context;

import com.randomizer.android.app.interactor.impl.BaseInteractorImpl;
import com.randomizer.android.utils.PreferencesUtils;

import javax.inject.Inject;

public final class SplashInteractorImpl extends BaseInteractorImpl implements SplashInteractor {

    private final Context mContext;

    private final PreferencesUtils mPreferencesUtils;

    @Inject
    public SplashInteractorImpl(Context context, PreferencesUtils preferencesUtils) {
        this.mContext = context;
        this.mPreferencesUtils = preferencesUtils;
    }

    @Override
    public boolean isNetworkConnected() {
        return super.isNetworkConnected(mContext);
    }

    @Override
    public boolean isSplashDone() {
        return mPreferencesUtils.getBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name());
    }

    @Override
    public void setSpalshDone() {
        mPreferencesUtils.putBoolean(PreferencesUtils.PrefKeys.IS_SPLASH_DONE.name(), true);

    }

}