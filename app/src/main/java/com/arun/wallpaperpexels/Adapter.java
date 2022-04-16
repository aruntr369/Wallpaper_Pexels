package com.arun.wallpaperpexels;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ImageModel> wallpaperList;

    public Adapter(Context context, ArrayList<ImageModel> wallpaperList) {
        this.context = context;
        this.wallpaperList = wallpaperList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.each_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//        //setMovement method used becz of cardview alredy have onclick
//        holder.textView.setMovementMethod(LinkMovementMethod.getInstance());
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
//                browserIntent.setData(Uri.parse("https://www.pexels.com/"));
//                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(browserIntent);
//            }
//        });
//
//            }
//        });
        Glide.with(context).load(wallpaperList.get(position).getSrc().getPortrait()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SetWallpapper.class);
                intent.putExtra("image",wallpaperList.get(position).getSrc().getPortrait());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.scale_up));

    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageviewItems);
            cardView = itemView.findViewById(R.id.cardViewItems);

        }
    }
}
