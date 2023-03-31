package com.example.MoviesCRUD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList movie_id, movie_title, movie_genre, movie_year;

    MoviesAdapter(Activity activity, Context context, ArrayList movie_id, ArrayList movie_title, ArrayList movie_genre,
                  ArrayList movie_year){
        this.activity = activity;
        this.context = context;
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.movie_genre = movie_genre;
        this.movie_year = movie_year;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.added_movies_form, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.movie_id_txt.setText(String.valueOf(movie_id.get(position)));
        holder.movie_title_txt.setText(String.valueOf(movie_title.get(position)));
        holder.movie_genre_txt.setText(String.valueOf(movie_genre.get(position)));
        holder.movie_year_txt.setText(String.valueOf(movie_year.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateMoviesActivity.class);
                intent.putExtra("id", String.valueOf(movie_id.get(position)));
                intent.putExtra("title", String.valueOf(movie_title.get(position)));
                intent.putExtra("genre", String.valueOf(movie_genre.get(position)));
                intent.putExtra("year", String.valueOf(movie_year.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movie_id_txt, movie_title_txt, movie_genre_txt, movie_year_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_id_txt = itemView.findViewById(R.id.movie_id_txt);
            movie_title_txt = itemView.findViewById(R.id.movie_title_txt);
            movie_genre_txt = itemView.findViewById(R.id.movie_genre_txt);
            movie_year_txt = itemView.findViewById(R.id.movie_year_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
