package com.randomizer.android.feature.users.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.randomizer.android.R;
import com.randomizer.android.feature.users.view.holder.UserItemHolder;
import com.randomizer.android.model.RandomUser;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserItemHolder> {

    private static final String TAG = UsersAdapter.class.getSimpleName();

    private List<RandomUser> mList = new ArrayList<>();

    @Override
    public UserItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.users_list_item, parent, false);
        return new UserItemHolder(view);
    }

    @Override
    public void onBindViewHolder(UserItemHolder holder, int position) {
        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<RandomUser> getMovieList() {
        return mList;
    }

    public void clearList() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void addList(List<RandomUser> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }
}

