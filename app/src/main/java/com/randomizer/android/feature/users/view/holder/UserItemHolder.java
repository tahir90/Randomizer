package com.randomizer.android.feature.users.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.randomizer.android.R;
import com.randomizer.android.feature.users.view.impl.UsersActivity;
import com.randomizer.android.model.RandomUser;
import com.randomizer.android.utils.AppUtils;
import com.randomizer.android.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.imageView_poster)
    protected ImageView mLogoImageView;
    @BindView(R.id.img_progress)
    protected ProgressBar imageProgressBar;

    @BindView(R.id.textview_name)
    protected TextView mNameTextView;
    @BindView(R.id.textview_gender)
    protected TextView mGenderTextView;
    @BindView(R.id.textview_email)
    protected TextView mEmailTextView;
    @BindView(R.id.textview_phone)
    protected TextView mPhoneTextView;
    @BindView(R.id.textview_address)
    protected TextView mAddressTextView;


    private final Context mContext;
    private RandomUser mRandomUser;

    /**
     * Holder default constructor
     * @param itemView
     */
    public UserItemHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    /**
     * Binds Data Object with the row Item
     * @param randomUser
     */
    public void bindData(RandomUser randomUser) {
        mRandomUser = randomUser;

        setValues();
    }

    /**
     * Setting values for the Ui widgets
     */
    private void setValues() {

        mNameTextView.setText(AppUtils.getFormatedName(mRandomUser));
        mGenderTextView.setText(AppUtils.getFormatedGender(mRandomUser));
        mEmailTextView.setText(mRandomUser.getEmail());
        mPhoneTextView.setText(mRandomUser.getPhone());
        mAddressTextView.setText(mRandomUser.getAddress());

        ImageUtils.loadImage(mContext, mLogoImageView, imageProgressBar,  mRandomUser.getImageUrl());
    }

    /**
     * Stub to provide click listener
     * @param view
     */
    @Override
    public void onClick(View view) {
        ((UsersActivity)view.getContext()).showToast("Clicked:" + AppUtils.getFormatedName(mRandomUser));
    }
}
