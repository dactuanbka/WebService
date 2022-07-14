package com.example.retrofitassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ActivityDetailBinding;
import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.utils.BindingUtils;

public class DetailActivity extends AppCompatActivity {
    private BindingUtils bindingUtils;
    private MarsProperty marsProperty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        bindingUtils = new BindingUtils();
        Intent intent = getIntent();
        marsProperty = (MarsProperty) intent.getSerializableExtra("DETAIL_REALESTATE");
        if (marsProperty != null) {
            BindingUtils.setImageUrl(detailBinding.imgDetail, marsProperty.getImgSrcUrl());
            detailBinding.txtTypeDetail.setText(marsProperty.getType());
            long Price= Math.round(marsProperty.getPrice());
            detailBinding.txtPriceDetail.setText("$" + Price);
        } else {
            Log.i("marsProperty_Detail", "NULL");
        }
    }
}