package com.example.neeraj_atg_task_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    FrameLayout frg_container;
    ConstraintLayout welcome_layout;

    String search_q_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frg_container = findViewById(R.id.frg_container);
        welcome_layout = findViewById(R.id.welcome_layout);

        SharedPreferences sharedPref_search_query = getSharedPreferences("save_search_query_sp", Context.MODE_PRIVATE);

        search_q_str = sharedPref_search_query.getString("search_query_sp","empty_str");



        if(frg_container != null){

            if(savedInstanceState != null)
                return;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    getSupportFragmentManager().beginTransaction().add(frg_container.getId(),new search_fragment(),"set_frg").commit();
                    welcome_layout.setVisibility(View.GONE);
                }
            }, 1000);


        }


    }

}