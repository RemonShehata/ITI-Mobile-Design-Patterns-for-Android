package com.iti.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iti.myapplication.model.LocalDataSource;
import com.iti.myapplication.model.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieDaoList;
    private List<Movie> moviesToDelete;
    public static final int REQUEST_CODE = 100;
    private RecyclerView moviesRecyclerView;
    private MainAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayout noMovies;
    private LocalDataSource db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        moviesToDelete = new ArrayList<>();
    }


    public void goToAddMovieActivity(View view) {
        Intent addIntent = new Intent(MainActivity.this, AddMovieActivity.class);
        startActivityForResult(addIntent, REQUEST_CODE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        movieDaoList = new ArrayList<>();
        db = new LocalDataSource(getApplication());
        movieDaoList = db.allMovies();
        displayResults();
    }

    private void setupViews() {
        moviesRecyclerView = findViewById(R.id.movies_recyclerview);
        noMovies = findViewById(R.id.no_movies_layout);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void displayResults() {
        if (movieDaoList == null || movieDaoList.size() == 0) {
            noMovies.setVisibility(VISIBLE);
            moviesRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            noMovies.setVisibility(View.INVISIBLE);
            moviesRecyclerView.setVisibility(VISIBLE);
            adapter = new MainAdapter(movieDaoList, this);
            layoutManager = new LinearLayoutManager(this);
            moviesRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
        for (Movie movie : moviesToDelete) {
            db.delete(movie);
        }
        displayResults();
        return super.onOptionsItemSelected(item);
    }
}
