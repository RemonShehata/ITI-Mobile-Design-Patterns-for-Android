package com.iti.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iti.myapplication.model.Movie;
import com.iti.myapplication.network.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MoviesHolder> {

    private Context context;
    private List<Movie> movieList;
    private Movie currentMovie;

    MainAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movie_main, parent, false);
        final MoviesHolder viewHolder = new MoviesHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        this.currentMovie = this.movieList.get(position);
        holder.movieTitle.setText(currentMovie.getTitle());
        holder.releaseDate.setText(currentMovie.getReleaseDate());
        Glide.with(context)
                .load(RetrofitClient.TMDB_IMAGEURL + currentMovie.getPosterPath())
                .placeholder(R.drawable.ic_local_movies_gray)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MoviesHolder extends RecyclerView.ViewHolder {

        private RecyclerView moviesRecyclerView;
        private ImageView movieImage;
        private TextView releaseDate;
        private TextView movieTitle;
        private CardView rootCardView;
        private CheckBox checkBox;

        public MoviesHolder(@NonNull View itemView) {
            super(itemView);
            rootCardView = itemView.findViewById(R.id.root);
            moviesRecyclerView = itemView.findViewById(R.id.movies_recyclerview);
            movieImage = itemView.findViewById(R.id.movie_imageview);
            releaseDate = itemView.findViewById(R.id.release_date_textview);
            movieTitle = itemView.findViewById(R.id.title_textview);
            //checkBox = itemView.findViewById(R.id.)
        }
    }


}
