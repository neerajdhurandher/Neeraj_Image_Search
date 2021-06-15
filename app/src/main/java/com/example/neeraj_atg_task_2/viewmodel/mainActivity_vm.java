package com.example.neeraj_atg_task_2.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.repositry.mainActivity_repo;

import java.util.List;

public class mainActivity_vm extends AndroidViewModel {


    public MutableLiveData<Example_model> forMutableLiveData;

    public mainActivity_vm(@NonNull Application application) {
        super(application);
        SharedPreferences sharedPref_search_query = application.getSharedPreferences("save_search_query_sp", Context.MODE_PRIVATE);

        String search_string = sharedPref_search_query.getString("search_query_sp","empty_str");



        mainActivity_repo mainActivityRepo = new mainActivity_repo(search_string);
        forMutableLiveData = mainActivityRepo.forMutableLiveData;
    }
}
