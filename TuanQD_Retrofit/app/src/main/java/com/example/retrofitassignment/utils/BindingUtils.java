package com.example.retrofitassignment.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.example.retrofitassignment.R;
import com.squareup.picasso.Picasso;


public class BindingUtils {
    @BindingAdapter("bind:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageDrawable(null);
        } else {
            url = url.replace("http", "https");
            Picasso.with(imageView.getContext())
                    .load(url)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_img)
                    .resize(600, 600)
                    .into(imageView);
        }
    }
}
