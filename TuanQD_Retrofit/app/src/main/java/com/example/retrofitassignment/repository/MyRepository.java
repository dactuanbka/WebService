package com.example.retrofitassignment.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.network.client.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyRepository {
    private static MyRepository myRepository;
    private MyRepository(){}

    public static MyRepository getInstance() {
        if (myRepository == null) {
            myRepository = new MyRepository();
        }
        return myRepository;
    }

    public LiveData<List<MarsProperty>> getMarsPropertyList(String filter) {
        final MutableLiveData<List<MarsProperty>> data = new MutableLiveData<>();
        ApiUtils.getRetrofitAPIService().getProperties(filter)
                .enqueue(new Callback<List<MarsProperty>>() {
                    @Override
                    public void onResponse(Call<List<MarsProperty>> call, Response<List<MarsProperty>> response) {
                        Log.d("MyRepository.class", response.body().toString());
                        data.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<MarsProperty>> call, Throwable t) {

                    }
                });
        return data;
    }

}
