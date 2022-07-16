package com.example.retrofitassignment.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.retrofitassignment.R;
import com.example.retrofitassignment.databinding.ActivityMainBinding;
import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.repository.MyRepository;
import com.example.retrofitassignment.view.adapter.ImageAdapter;
import com.example.retrofitassignment.viewmodel.GeneralViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    private final List<MarsProperty> propertyList = new ArrayList<>();
    private static final String BUY_FILTER = "buy";
    private static final String RENT_FILTER = "rent";
    private static final String DEFAULT_FILTER = BUY_FILTER;

    private GeneralViewModel generalViewModel;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        setSupportActionBar(mainBinding.toolBar);
        MyRepository myRepository = MyRepository.getInstance();
        generalViewModel = new GeneralViewModel(myRepository);
        generalViewModel.getMarsPropertyList(DEFAULT_FILTER).observe(this,
                marsProperties -> {
                    generalViewModel.getMykey().setValue(DEFAULT_FILTER);
                    imageAdapter.setMarsPropertyList(marsProperties);
                    mainBinding.setViewModel(generalViewModel);
                    mainBinding.executePendingBindings();
                });
        imageAdapter = new ImageAdapter(this, propertyList, this);
        mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mainBinding.recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_rent:
                loadRentItems();
                break;
            case R.id.menu_item_buy:
                loadBuyItems();
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(View view, int position, boolean isLongClick) {

    }

    private void loadRentItems() {
        generalViewModel.getMarsPropertyList(RENT_FILTER).observe(this,
                new Observer<List<MarsProperty>>() {
                    @Override
                    public void onChanged(List<MarsProperty> marsProperties) {
                        imageAdapter.setMarsPropertyList(marsProperties);
                        generalViewModel.getMykey().setValue(RENT_FILTER);

                    }
                });
    }

    private void loadBuyItems() {
        generalViewModel.getMarsPropertyList(BUY_FILTER).observe(this,
                new Observer<List<MarsProperty>>() {
                    @Override
                    public void onChanged(List<MarsProperty> marsProperties) {
                        imageAdapter.setMarsPropertyList(marsProperties);
                        generalViewModel.getMykey().setValue(BUY_FILTER);

                    }
                });
    }
}