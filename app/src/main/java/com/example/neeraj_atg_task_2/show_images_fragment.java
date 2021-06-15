package com.example.neeraj_atg_task_2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.models.Photo;
import com.example.neeraj_atg_task_2.models.Photos;
import com.example.neeraj_atg_task_2.models.search_query;
import com.example.neeraj_atg_task_2.paging.PhotoDataSource;
import com.example.neeraj_atg_task_2.paging.PhotoListAdapter;
import com.example.neeraj_atg_task_2.paging.PhotoViewModel;
import com.example.neeraj_atg_task_2.repositry.mainActivity_repo;
import com.example.neeraj_atg_task_2.viewmodel.mainActivity_vm;

import java.util.List;


public class show_images_fragment extends Fragment {


    RecyclerView recyclerView;
    String search_string;
    PhotoViewModel photoViewModel;
    PhotoListAdapter photoListAdapter;
    ProgressDialog pd;
     com.example.neeraj_atg_task_2.repositry.mainActivity_repo mainActivity_repo;

     PhotoDataSource photoDataSource ;
     adaptershowphotos adaptershowphotos;

    List<Photo> list;

    public show_images_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_show_images_fragment, container, false);

        pd = new ProgressDialog(getContext());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(true);
        pd.setProgress(50);
        pd.setMessage("Please Wait");
        pd.show();

        mainActivity_vm mainActivityVm = new ViewModelProvider(show_images_fragment.this).get(mainActivity_vm.class);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        SharedPreferences sharedPref_search_query = getActivity().getSharedPreferences("save_search_query_sp", Context.MODE_PRIVATE);

        search_string = sharedPref_search_query.getString("search_query_sp","empty_str");


        Log.d("get_data","show_img_frg " + search_string);

        mainActivity_repo = new mainActivity_repo(search_string);

        photoDataSource = new PhotoDataSource(search_string);

        photoViewModel = ViewModelProviders.of(getActivity()).get(PhotoViewModel.class);


//        photoViewModel.getPhotopagedlist().observe(getActivity(), new Observer<Example_model>() {
//            @Override
//            public void onChanged(Example_model example_model) {
//
//                    Log.d("get_data","observer call");
//
//                    Photos ph = example_model.getPhotos();
//                    ph.setPage(1);
//                    list = ph.getPhoto();
//
//                Log.d("ff" , "size of photo list in frg " + list.size());
//
//                photoListAdapter = new PhotoListAdapter(getActivity(),list);
//                recyclerView.setAdapter(photoListAdapter);
//
//            }
//        });


        Observer<Example_model> observer = new Observer<Example_model>() {
            @Override
            public void onChanged(Example_model example_model) {


                    Log.d("get_data","observer call");

                    Photos ph = example_model.getPhotos();
                    ph.setPage(1);
                    list = ph.getPhoto();

                    Log.d("ff" , "size of photo list in frg " + list.size());

                    adaptershowphotos = new adaptershowphotos(getActivity(),list);
                    recyclerView.setAdapter(adaptershowphotos);

            }
        };

        mainActivityVm.forMutableLiveData.observe(getViewLifecycleOwner(),observer);

        recyclerView.setAdapter(adaptershowphotos);
        pd.dismiss();



        return view;
    }

}