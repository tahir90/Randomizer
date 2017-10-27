package com.randomizer.android.feature.users.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.randomizer.android.R;
import com.randomizer.android.app.injection.AppComponent;
import com.randomizer.android.app.presenter.loader.PresenterFactory;
import com.randomizer.android.app.view.impl.BaseActivity;
import com.randomizer.android.feature.users.injection.DaggerUsersViewComponent;
import com.randomizer.android.feature.users.injection.UsersViewModule;
import com.randomizer.android.feature.users.presenter.UsersPresenter;
import com.randomizer.android.feature.users.view.UsersView;
import com.randomizer.android.feature.users.view.adapter.UsersAdapter;
import com.randomizer.android.model.RandomUser;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.randomizer.android.constants.AppConstants.DEFAULT_PAGE_SIZE;
import static com.randomizer.android.constants.AppConstants.LAST_PAGE_NUMBER;

public final class UsersActivity extends BaseActivity<UsersPresenter, UsersView> implements UsersView {
    @Inject
    PresenterFactory<UsersPresenter> mPresenterFactory;

    @BindView(R.id.recyclerView_users)
    protected RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private UsersAdapter mAdapter;

    private List<RandomUser> mList = new ArrayList<>();

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

    /**
     * Smart substitution of onCreate
     * Life cycle will be handled by the Parent Activity
     * @param savedInstanceState
     * @param intent
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        initList();
    }

    private void initList() {

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new UsersAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }


    /**
     * includes utility functions required for pagination
     */
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

            assert mPresenter != null;
            if (!mPresenter.isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= DEFAULT_PAGE_SIZE) {
                    loadNextPage();
                }
            }
        }

        private void loadNextPage() {
            assert mPresenter != null;
            mPresenter.fetchNextPage(getNextPageNumber());
        }

        private int getNextPageNumber() {
            return (mList.size() / DEFAULT_PAGE_SIZE) + 1;
        }

        private boolean isLastPage() {
            return mList.size() / DEFAULT_PAGE_SIZE == LAST_PAGE_NUMBER;
        }

    };

    @Override
    public void loadList(List<RandomUser> list) {
        mList.addAll(list);
        showList();
    }

    /**
     * resets list of Adapter
     */
    private void showList() {
        mAdapter.clearList();
        mAdapter.addMovies(mList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
