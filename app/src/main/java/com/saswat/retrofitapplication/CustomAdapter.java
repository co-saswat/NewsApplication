package com.saswat.retrofitapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList<Article> articles;
    Activity activity;

    public CustomAdapter(Activity activity, Context context, ArrayList<Article> articles) {
        this.activity = activity;
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(articles.get(position).urlToImage).into(holder.img_view_news);
        holder.tv_title_name.setText(articles.get(position).title);
        holder.tv_content_name.setText(articles.get(position).content);
        holder.tv_read_more.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(articles.get(position).url));
            activity.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_view_news;
        TextView tv_title_name;
        TextView tv_content_name;
        TextView tv_read_more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_view_news = itemView.findViewById(R.id.img_news_image);
            tv_content_name = itemView.findViewById(R.id.tv_content_news);
            tv_title_name = itemView.findViewById(R.id.tv_title_news);
            tv_read_more = itemView.findViewById(R.id.tv_read_more);

        }
    }
}
