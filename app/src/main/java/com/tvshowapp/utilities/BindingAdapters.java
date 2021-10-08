package com.tvshowapp.utilities;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter("android:imageURL")
    public static void setImageUrl(ImageView view, String url) {
        try {
            view.setAlpha(0f);
            Picasso.get().load(url).noFade().into(view, new Callback() {
                @Override
                public void onSuccess() {
                    view.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {

                }
            });
        } catch (Exception ex) {

        }
    }

}
