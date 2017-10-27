package com.randomizer.android.feature.users;

import com.randomizer.android.feature.users.interactor.UsersInteractor;
import com.randomizer.android.feature.users.presenter.UsersPresenter;
import com.randomizer.android.feature.users.presenter.impl.UsersPresenterImpl;
import com.randomizer.android.feature.users.view.UsersView;
import com.randomizer.android.model.RandomUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsersPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private UsersInteractor mInteractor;

    @Mock
    private UsersView mView;

    private UsersPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new UsersPresenterImpl(mInteractor);
        mPresenter.onViewAttached(mView);
    }


    @Test
    public void shouldPassOnStart() throws Exception {

        mPresenter.onStart(true);

        assertNotNull(mView);
    }


    @Test
    public void shouldShowInternetError() throws Exception {

        //Given
        when(mInteractor.isNetworkConnected()).thenReturn(false);

        //When
        mPresenter.fetchNextPage(new Random().nextInt());

        //Then
        verify(mView).showNetworkError();
    }

    @Test
    public void showLoadingDialog() throws Exception {

        mPresenter.onStart();

        mView.showLoading();
    }

    @Test
    public void passWhenDataFound() throws Exception {

        mPresenter.onDataResponse(Arrays.asList(new RandomUser()));

        mView.hideLoading();
    }

    @Test
    public void shouldLoadList() throws Exception {
        List<RandomUser> list = Collections.singletonList(new RandomUser());

        //Given
        when(mInteractor.isNetworkConnected()).thenReturn(true);

        //When
        mPresenter.onDataResponse(list);

        //Then
        verify(mView).loadList(list);
    }

}