package com.example.neeraj_atg_task_2.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.models.Photo;

public class PhotoViewModel extends ViewModel {

   private LiveData photopagedlist;
   private LiveData<PageKeyedDataSource<Integer, Photo>> liveDataSource;
    public MutableLiveData<Example_model> forMutableLiveData;


     public PhotoViewModel(){
         PhotoDataSourceFactory photoDataSourceFactory = new PhotoDataSourceFactory();
         forMutableLiveData = photoDataSourceFactory.getPhotoLiveData();
         PagedList.Config config = new PagedList.Config.Builder()
                 .setEnablePlaceholders(true)
                 .setPageSize(5)
                 .build();

         photopagedlist = new LivePagedListBuilder(photoDataSourceFactory,config).build();

     }

     public LiveData getPhotopagedlist (){
         return photopagedlist;
     }




}
