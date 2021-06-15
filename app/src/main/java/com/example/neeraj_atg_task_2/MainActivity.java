package com.example.neeraj_atg_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    FrameLayout frg_container;

    String search_q_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frg_container = findViewById(R.id.frg_container);


        if(frg_container != null){

            if(savedInstanceState != null)
                return;

            getSupportFragmentManager().beginTransaction().add(frg_container.getId(),new search_fragment(),"set_frg").addToBackStack(null).commit();

            SharedPreferences sharedPref_search_query = getSharedPreferences("save_search_query_sp", Context.MODE_PRIVATE);

            search_q_str = sharedPref_search_query.getString("search_query_sp","empty_str");

        }



    }
}