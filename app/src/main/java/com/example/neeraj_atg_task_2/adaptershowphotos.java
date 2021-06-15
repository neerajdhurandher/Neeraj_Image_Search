package com.example.neeraj_atg_task_2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.neeraj_atg_task_2.models.Photo;

import java.util.List;

public class adaptershowphotos extends RecyclerView.Adapter<adaptershowphotos.photoviewHolder> {

    Context context;
    List<Photo> photoList;

    public adaptershowphotos(Context context , List<Photo>photoList){
        this.context = context;
        this.photoList = photoList;

    }

    @NonNull
    @Override
    public photoviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_blueprint, parent,false);
        return new photoviewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull photoviewHolder holder, int position) {

            Photo photo = photoList.get(position);

            holder.title.setText(photo.getTitle());
            holder.owner.setText(photo.getOwner());


            Log.d("get_data" ,"title in adapter " + photo.getTitle());

            try {
                Glide.with(context).load(photo.getUrlS()).into(holder.photo_show);
            } catch (Exception e) {
                Glide.with(context).load(R.drawable.ic_photo).into(holder.photo_show);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent clicked_photo = new Intent( context,Photo_Details_Activity.class);

                    clicked_photo.putExtra("photo",photo.getUrlS());
                    clicked_photo.putExtra("title",photoList.get(position).getTitle());
                    clicked_photo.putExtra("owner",photoList.get(position).getOwner());
                    clicked_photo.putExtra("id",photoList.get(position).getId());

                    context.startActivity(clicked_photo);

                }
            });


    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class photoviewHolder extends RecyclerView.ViewHolder{
        ImageView photo_show;
        TextView title,owner;
        public photoviewHolder(@NonNull View itemView) {
            super(itemView);
            photo_show = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title_txt);
            owner = itemView.findViewById(R.id.owner_txt);
        }
    }
}
