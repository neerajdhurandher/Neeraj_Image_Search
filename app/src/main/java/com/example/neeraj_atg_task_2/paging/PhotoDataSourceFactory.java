package com.example.neeraj_atg_task_2.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.models.Photo;

import org.jetbrains.annotations.NotNull;

import java.net.DatagramSocketImpl;
import java.net.DatagramSocketImplFactory;

public class PhotoDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<Example_model> photoLiveData = new MutableLiveData<>();

    @NotNull
    @Override
    public DataSource create() {
        PhotoDataSource photoDataSource = new PhotoDataSource("");
        photoLiveData = photoDataSource.forMutableLiveData;

        return photoDataSource;
    }

    public MutableLiveData<Example_model> getPhotoLiveData() {
        return photoLiveData;
    }
}
