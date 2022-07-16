package com.example.retrofitassignment.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitassignment.BR;
import com.example.retrofitassignment.R;
import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.repository.MyRepository;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GeneralViewModel extends ViewModel {
    private static MutableLiveData<String> myKey;
    private final MyRepository repository;

    public GeneralViewModel(MyRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<String> getMykey() {
        if (myKey == null) {
            myKey = new MutableLiveData<String>();
        }
        return myKey;
    }

    public LiveData<List<MarsProperty>> getMarsPropertyList(String key) {
        return repository.getMarsPropertyList(key); // sử dụng instance singleton
    }

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

    @BindingAdapter("bind:text")
    public static void setType(TextView textView, String type) {
        if (type == null) {
            textView.setText(null);
        } else {
            textView.setText(type);
            Log.i("TEXT", "" + type);
        }

    }

    @BindingAdapter("bind2:text")
    public static void setPrice(TextView textView, String price) {
        if (price == null) {
            textView.setText(null);
        } else {
            textView.setText(price);
        }
    }

}
