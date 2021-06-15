package com.example.neeraj_atg_task_2.paging;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.models.Photo;
import com.example.neeraj_atg_task_2.models.search_query;
import com.example.neeraj_atg_task_2.restApiHelper.RestApiHelp;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoDataSource extends PageKeyedDataSource<Integer, Photo> {

    public static final int First_Page = 1;

    public MutableLiveData<Example_model> forMutableLiveData = new MutableLiveData<>();
     RestApiHelp restApiHelp;

    public String getSearch_query_search_str() {
        return search_query_search_str;
    }

    public void setSearch_query_search_str(String search_query_search_str) {
        this.search_query_search_str = search_query_search_str;
    }

    String search_query_search_str;

    public PhotoDataSource(String get_query_string) {
        setSearch_query_search_str( get_query_string);
//        get_paging_data();
    }


    @Override
    public void loadAfter(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, Photo> loadCallback) {

        get_paging_data();
    }

    @Override
    public void loadBefore(@NotNull LoadParams<Integer> loadParams, @NotNull LoadCallback<Integer, Photo> loadCallback) {

    }

    @Override
    public void loadInitial(@NotNull LoadInitialParams<Integer> loadInitialParams, @NotNull LoadInitialCallback<Integer, Photo> loadInitialCallback) {

//            get_paging_data();
    }


    private void get_paging_data() {

        restApiHelp = RestApiHelp.retrofit.create(RestApiHelp.class);

        Call<Example_model> data_call = restApiHelp.data_from_api(search_query_search_str);

//        Call<Example_model> data_call = restApiHelp.data_from_api();


        Log.d("get_data", search_query_search_str + " call in Data Source");


        data_call.enqueue(new Callback<Example_model>()  {
            @Override
            public void onResponse(Call<Example_model> call, Response<Example_model> response) {

                if(response.body() != null) {

                    Example_model Example_model = response.body();
                    forMutableLiveData.setValue(Example_model);

                    Log.d("repo", "data sourece repo : " + response.toString());

                    Log.d("get_data", "Successful in Data Source");

                }

            }

            @Override
            public void onFailure(Call<Example_model> call, Throwable t) {

                Log.d("get_data","on Failure in Data Source "+t);
            }
        });


    }
}
