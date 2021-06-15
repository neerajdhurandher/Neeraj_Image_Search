package com.example.neeraj_atg_task_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Photo_Details_Activity extends AppCompatActivity {
    TextView title,owner,id;
    ImageView show_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo__details_);


        show_photo = findViewById(R.id.show_click_photo);
        title = findViewById(R.id.title_text);
        owner  = findViewById(R.id.owner_text);
        id  = findViewById(R.id.id_text);

        String get_owner = getIntent().getExtras().getString("owner");
        String get_title = getIntent().getExtras().getString("title");
        String get_id = getIntent().getExtras().getString("id");
        String get_photo = getIntent().getExtras().getString("photo");

        title.setText(get_title);
        owner.setText(get_owner);
        id.setText(get_id);

        try {
            Glide.with(getApplicationContext()).load(get_photo).into(show_photo);
        }
        catch (Exception e){
            Glide.with(getApplicationContext()).load(R.drawable.ic_menu_gallery).into(show_photo);
        }



    }
}