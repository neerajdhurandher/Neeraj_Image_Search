package com.example.neeraj_atg_task_2.repositry;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.models.search_query;
import com.example.neeraj_atg_task_2.restApiHelper.RestApiHelp;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainActivity_repo  {

    private final Executor executor = Executors.newSingleThreadExecutor();
    public MutableLiveData<Example_model> forMutableLiveData = new MutableLiveData<>();
    private RestApiHelp restApiHelp;

    String search_query_search_str = "dog";

    public mainActivity_repo(String get_search_query) {

        this.search_query_search_str = get_search_query;

        getdatafromApi();

    }

    private void getdatafromApi() {
        executor.execute(new Runnable() {
            @Override
            public void run() {


                restApiHelp = RestApiHelp.retrofit.create(RestApiHelp.class);

                Call<Example_model> data_call = restApiHelp.data_from_api(search_query_search_str);

                Log.d("get_data", search_query_search_str + " call in main_activity_repo ");


                data_call.enqueue(new Callback<Example_model>()  {
                    @Override
                    public void onResponse(Call<Example_model> call, Response<Example_model> response) {

                        Log.d("repo" , response.toString() );

                        Example_model Example_model = response.body();

                        forMutableLiveData.setValue(Example_model);



                        Log.d("get_data" , "Successful in main_activity_repo" );
                        Log.d("get_data" , "Successful in main_activity_repo size " + Example_model.getPhotos().getPhoto().size() );
                        Log.d("get_data" , "Successful in main_activity_repo title " + Example_model.getPhotos().getPhoto().get(1).getTitle()  );

                    }

                    @Override
                    public void onFailure(Call<Example_model> call, Throwable t) {

                        Log.d("get_data","on Failure in main_activity_repo "+t);
                    }
                });




            }
        });
    }



}
