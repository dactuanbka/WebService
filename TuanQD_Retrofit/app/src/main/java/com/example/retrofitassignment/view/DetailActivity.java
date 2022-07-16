package com.example.retrofitassignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ActivityDetailBinding;
import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.repository.MyRepository;
import com.example.retrofitassignment.viewmodel.GeneralViewModel;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private GeneralViewModel detailViewModel;
    private MarsProperty marsProperty;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding detailBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_detail);
        Intent intent = getIntent();
        marsProperty = (MarsProperty) intent.getSerializableExtra("DETAIL_REALESTATE");
        MyRepository myRepository = MyRepository.getInstance();
        detailViewModel = new GeneralViewModel(myRepository);
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i("DETAL_TYPE", s + " AND " + position);
                detailBinding.setModel(marsProperty);
                detailBinding.executePendingBindings();
            }
        };
        detailViewModel.getMykey().observe(this, nameObserver);

    }
}