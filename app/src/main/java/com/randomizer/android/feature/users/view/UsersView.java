package com.randomizer.android.feature.users.view;

import android.support.annotation.UiThread;

import com.randomizer.android.app.view.BaseView;
import com.randomizer.android.model.RandomUser;

import java.util.List;

@UiThread
public interface UsersView extends BaseView {

    void loadList(List<RandomUser> list);

}