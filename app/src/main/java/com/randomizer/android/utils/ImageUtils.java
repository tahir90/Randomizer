package com.randomizer.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.randomizer.android.R;

import timber.log.Timber;

public class ImageUtils {

    public static void loadImage(final Context context, final ImageView imageView, final ProgressBar progressBar, String url) {

        Timber.e("Path: " + url);
        Glide.with(context)
                .load((url == null) ? R.mipmap.ic_launcher_round : url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap arg0, GlideAnimation<? super Bitmap> arg1) {
                        progressBar.setVisibility(View.GONE);
                        imageView.setImageBitmap(arg0);
                    }
                });
    }
}
