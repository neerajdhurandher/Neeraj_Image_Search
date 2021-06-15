package com.example.neeraj_atg_task_2.restApiHelper;

import com.example.neeraj_atg_task_2.MainActivity;
import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.paging.PhotoDataSource;

import java.net.URI;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApiHelp {


//    String BASE_URL = " https://api.flickr.com/services/rest/";

//    String search_txt_method = "?method=flickr.photos.search&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s&text={search_txt}";
 String BASE_URL = " https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s&";

    String search_txt_method = "text={text}";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    @GET(BASE_URL)
    Call<Example_model> data_from_api(@Query("text") String search_query_search_str );
//    Call<Example_model> data_from_api();


}
