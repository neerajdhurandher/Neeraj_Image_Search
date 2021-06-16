package com.example.neeraj_atg_task_2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neeraj_atg_task_2.models.Example_model;
import com.example.neeraj_atg_task_2.models.search_query;
import com.example.neeraj_atg_task_2.repositry.mainActivity_repo;
import com.example.neeraj_atg_task_2.restApiHelper.RestApiHelp;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class search_fragment extends Fragment {

    EditText search_text;
    Button search_btn;
    String txtSearchString = "neeraj_search_null";
    ProgressDialog pd;
    search_query search_query;


    private CompositeDisposable disposable = new CompositeDisposable();
    private Unbinder unbinder;
    private long timeSinceLastRequest;

    com.example.neeraj_atg_task_2.repositry.mainActivity_repo mainActivity_repo;




    public search_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_search_fragment, container, false);

        search_text = view.findViewById(R.id.search_text);
        search_btn = view.findViewById(R.id.search_btn);

        pd = new ProgressDialog(getContext());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Please Wait");

        timeSinceLastRequest = System.currentTimeMillis();

        txtSearchString = search_text.getText().toString();


        Log.d("get_data",   "Search fragment start");

        ConnectivityManager conMgr = (ConnectivityManager)getActivity().getSystemService (Context.CONNECTIVITY_SERVICE);


        if (conMgr.getActiveNetworkInfo() != null
                &&  conMgr.getActiveNetworkInfo().isAvailable()
                &&  conMgr.getActiveNetworkInfo().isConnected() ) {

            search_btn.setClickable(true);
        }

        else {

            search_btn.setClickable(false);

            Snackbar.make(view , "No Internet Connection", Snackbar.LENGTH_LONG)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getActivity(),MainActivity.class));
                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_dark ))
                    .show();
        }



        unbinder = ButterKnife.bind(getActivity());

        disposable.add(
                RxTextView.textChangeEvents(search_text)
                        .skipInitialValue()
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(searchQuery())
        );



        search_btn.setOnClickListener(v -> {

            if (!txtSearchString.equals("neeraj_search_null") && !txtSearchString.equals("")) {

                Log.d("get_data", txtSearchString + "  clicked");

                pd.show();


                mainActivity_repo = new mainActivity_repo(txtSearchString);

                SharedPreferences sharedPref_search_query = getActivity().getSharedPreferences("save_search_query_sp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor_sp = sharedPref_search_query.edit();
                editor_sp.putString("search_query_sp",txtSearchString);
                editor_sp.apply();
                search_text.setText("");
                search_text.setHint("Enter Here");
                search_text.clearFocus();
                pd.dismiss();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frg_container, new show_images_fragment(), "replace_frg").addToBackStack(null).commit();



            } else {

                Toast.makeText(getActivity(), "Enter some key words", Toast.LENGTH_SHORT).show();
                search_text.setText("");
                search_text.setHint("Enter Here");
                search_text.clearFocus();
            }

        });


        return view;
    }

    private DisposableObserver<TextViewTextChangeEvent> searchQuery() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {

                txtSearchString = textViewTextChangeEvent.getText().toString();

                Log.d("get_data", "search string: " + txtSearchString);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

                Log.d("get_data", "Complete Final search string: " + txtSearchString);
            }
        };


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        disposable.clear();
    }


}