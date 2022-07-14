package com.example.retrofitassignment.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofitassignment.model.MarsProperty;
import com.example.retrofitassignment.repository.MyRepository;

import java.util.List;

public class GeneralViewModel extends ViewModel {
    private MyRepository repository;
    public GeneralViewModel(MyRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<MarsProperty>> getMarsPropertyList(String key) {
        return repository.getMarsPropertyList(key);
    }

}
