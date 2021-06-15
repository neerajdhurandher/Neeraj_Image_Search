package com.example.neeraj_atg_task_2.paging;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.neeraj_atg_task_2.R;
import com.example.neeraj_atg_task_2.models.Photo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PhotoListAdapter extends PagedListAdapter<Photo,PhotoListAdapter.PhotoListViewHolder> {
    Context context;
    List<Photo> photoList;
    public PhotoListAdapter(Context context, List<Photo>photos){
        super(diffCallback);
        this.context = context;
        this.photoList = photos;

    }

    @NonNull
    @Override
    public PhotoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.photo_blueprint,parent,false
        );
        return new PhotoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoListViewHolder holder, int position) {

        Photo photo = photoList.get(position);
//        if (photo != null){

            holder.title.setText(photo.getTitle());
            holder.owner.setText(photo.getOwner());

            Log.d("get_data" ,"title in adapter " + photo.getTitle());

            try {
                Glide.with(context).load(photo.getUrlS()).into(holder.photo_show);
            } catch (Exception e) {
                Glide.with(context).load(R.drawable.ic_photo).into(holder.photo_show);
            }

//        }

    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public long getItemId(int position) {
        return 0;
    }


    private static DiffUtil.ItemCallback<Photo> diffCallback = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.equals(newItem);
        }
    };

    class PhotoListViewHolder extends RecyclerView.ViewHolder{

        ImageView photo_show;
        TextView title,owner;

        public PhotoListViewHolder(@NonNull View itemView) {
            super(itemView);

            photo_show = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title_txt);
            owner = itemView.findViewById(R.id.owner_txt);
        }
    }
}
