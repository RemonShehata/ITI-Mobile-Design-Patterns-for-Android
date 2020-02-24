package com.iti.myapplication.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.iti.myapplication.R;
import com.iti.myapplication.add.AddMovieActivity;
import com.iti.myapplication.model.LocalDataSource;
import com.iti.myapplication.model.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements MainContract.ViewInterface {

    public static final int REQUEST_CODE = 100;
    private RecyclerView moviesRecyclerView;
    private LinearLayout noMovies;
    private MainAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainContract.PresenterInterface mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        mainPresenter = new MainPresenter(getApplication(), this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.getMyMoviesList();
    }

    @Override
    public void displayMovies(List<Movie> movieList) {
        moviesRecyclerView.setVisibility(VISIBLE);
        noMovies.setVisibility(INVISIBLE);
        //mainPresenter.getMyMoviesList();

        adapter = new MainAdapter(movieList, this);
        layoutManager = new LinearLayoutManager(this);
        moviesRecyclerView.setAdapter(adapter);

    }

    @Override
    public void displayNoMovies() {
        moviesRecyclerView.setVisibility(INVISIBLE);
        noMovies.setVisibility(VISIBLE);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void goToAddMovieActivity(View view) {
        Intent addIntent = new Intent(MainActivity.this, AddMovieActivity.class);
        startActivityForResult(addIntent, REQUEST_CODE);
    }

    private void setupViews() {
        moviesRecyclerView = findViewById(R.id.movies_recyclerview);
        noMovies = findViewById(R.id.no_movies_layout);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
            mainPresenter.onDelete(MainAdapter.getSelectedMovies());
        mainPresenter.getMyMoviesList();
        return super.onOptionsItemSelected(item);
    }
}
