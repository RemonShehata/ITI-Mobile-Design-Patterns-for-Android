package com.iti.myapplication.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iti.myapplication.R;
import com.iti.myapplication.model.LocalDataSource;
import com.iti.myapplication.model.Movie;
import com.iti.myapplication.network.RetrofitClient;
import com.iti.myapplication.search.SearchActivity;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.iti.myapplication.network.RetrofitClient.TMDB_IMAGEURL;

public class AddMovieActivity extends AppCompatActivity implements AddMovieContract.ViewInterface {

    private EditText titleEditText;
    private EditText releaseDateEditText;
    private ImageView movieImageView;
    private LocalDataSource dataSource;
    public static final int SEARCH_MOVIE_ACTIVITY_REQUEST_CODE = 2;
    private AddMovieContract.PresenterInterface addMoviePresenter;
    private Movie movieToAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setupViews();
        dataSource = new LocalDataSource(getApplication());
        addMoviePresenter = new AddMoviePresenter(dataSource,this);
    }

    @Override
    public void returnToMain() {
        finish();
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onClickAddMovie(View view) {
        addMoviePresenter.addMovie(movieToAdd.getTitle(),
                movieToAdd.getReleaseDate(), movieToAdd.getPosterPath());
    }

    private void setupViews() {
        titleEditText = findViewById(R.id.movie_title);
        releaseDateEditText = findViewById(R.id.movie_release_date);
        movieImageView = findViewById(R.id.movie_imageview);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(SearchActivity.EXTRA_TITLE);
            String releaseDate = data.getStringExtra(SearchActivity.EXTRA_RELEASE_DATE);
            String imageUrl = data.getStringExtra(SearchActivity.EXTRA_POSTER_PATH);

            titleEditText.setText(title);
            releaseDateEditText.setText(releaseDate);
            Glide.with(this)
                    .load(RetrofitClient.TMDB_IMAGEURL + imageUrl)
                    .placeholder(R.drawable.ic_local_movies_gray)
                    .into(movieImageView);
            movieToAdd = new Movie(title, imageUrl, releaseDate);
        } else {

        }
    }

    public void goToSearchMovieActivity(View view) {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        searchIntent.putExtra(SearchActivity.SEARCH_QUERY, titleEditText.getText().toString());
        startActivityForResult(searchIntent, SEARCH_MOVIE_ACTIVITY_REQUEST_CODE);
    }
}
