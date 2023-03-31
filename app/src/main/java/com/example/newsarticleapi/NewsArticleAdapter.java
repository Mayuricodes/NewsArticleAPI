package com.example.newsarticleapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder>{

    Context context;
    ArrayList<ArticleModel>list = new ArrayList<>();

    public NewsArticleAdapter(Context context, ArrayList<ArticleModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewsArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articlerecycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleAdapter.ViewHolder holder, int position) {
        holder.author.setText(list.get(position).author);
        holder.title.setText(list.get(position).title);
        holder.description.setText(list.get(position).description);

        String url = list.get(position).imageurl;
        Picasso.get().load(url).placeholder(R.drawable.baseline_image_24).into(holder.imageurl);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,author,description;
        CardView view;
        ImageView imageurl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            view = itemView.findViewById(R.id.cardview);
            imageurl = itemView.findViewById(R.id.action_image);
        }
    }
}
