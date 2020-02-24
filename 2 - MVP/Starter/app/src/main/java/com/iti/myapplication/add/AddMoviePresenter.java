package com.iti.myapplication.add;

import android.app.Application;

import com.iti.myapplication.model.LocalDataSource;
import com.iti.myapplication.model.Movie;

public class AddMoviePresenter implements AddMovieContract.PresenterInterface {

    private AddMovieContract.ViewInterface addView;
    private LocalDataSource db;

    public AddMoviePresenter(LocalDataSource dataSource, AddMovieContract.ViewInterface addView) {
        this.addView = addView;
        db = dataSource;
    }

    @Override
    public void addMovie(String title, String releaseDate, String posterPath) {
        db.insert(new Movie(title, posterPath, releaseDate));
        addView.displayMessage("Movie added successfully");
        addView.returnToMain();
    }
}
