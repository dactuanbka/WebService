package com.example.retrofitassignment.network.client;
import com.example.retrofitassignment.network.RetrofitAPIService;

public class ApiUtils {
    public static RetrofitAPIService getRetrofitAPIService() {
        return RetrofitClient.getClient(RetrofitAPIService.BASE_URL)
                .create(RetrofitAPIService.class);
    }
}
