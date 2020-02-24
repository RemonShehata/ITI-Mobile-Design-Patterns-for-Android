package com.iti.myapplication.main;

import android.app.Application;

import com.iti.myapplication.model.LocalDataSource;
import com.iti.myapplication.model.Movie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainPresenter implements MainContract.PresenterInterface {

    private MainContract.ViewInterface mainView;
    private LocalDataSource db;

    public MainPresenter(Application app, MainContract.ViewInterface mainView) {
        this.mainView = mainView;
        db = new LocalDataSource(app);
    }

    @Override
    public void getMyMoviesList() {
        mainView.displayMovies(db.allMovies());

    }

    @Override
    public void onDelete(HashSet<Movie> selectedMovies) {
        for (Movie movie : selectedMovies) {
            db.delete(movie);
        }

    }

    @Override
    public void stop() {

    }
}
