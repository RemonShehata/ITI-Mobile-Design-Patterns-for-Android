package com.iti.myapplication.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.myapplication.R;
import com.iti.myapplication.model.Movie;
import com.iti.myapplication.model.RemoteDataSource;
import com.iti.myapplication.model.TmdbResponse;

import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SearchActivity extends AppCompatActivity implements SearchContract.ViewInterface {
    public static final String SEARCH_QUERY = "searchQuery";
    public static final String EXTRA_TITLE = "SearchActivity.TITLE_REPLY";
    public static final String EXTRA_RELEASE_DATE = "SearchActivity.RELEASE_DATE_REPLY";
    public static final String EXTRA_POSTER_PATH = "SearchActivity.POSTER_PATH_REPLY";

    private final String TAG = "SearchActivity";

    private RecyclerView searchResultsRecyclerView;
    private SearchAdapter adapter;
    private TextView noMoviesTextView;
    private ProgressBar progressBar;
    private String query;
    private SearchPresenter searchPresenter;
    private RemoteDataSource dataSource = new RemoteDataSource();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        setupViews();
        query = getIntent().getStringExtra(SEARCH_QUERY);
        searchPresenter = new SearchPresenter(this, dataSource);

    }

    @Override
    protected void onStart() {
        super.onStart();
        searchPresenter.getSearchResults(query);
    }

    private void setupViews() {
        searchResultsRecyclerView = findViewById(R.id.search_results_recyclerview);
        noMoviesTextView = findViewById(R.id.no_movies_textview);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void displayResult(TmdbResponse tmdbResponse) {

        List<Movie> movieList = tmdbResponse.getResults();
        searchResultsRecyclerView.setVisibility(VISIBLE);
        adapter = new SearchAdapter(movieList, this, itemListener);

        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRecyclerView.setAdapter(adapter);

    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private RecyclerItemListener itemListener =
            new SearchActivity.RecyclerItemListener() {
                public void onItemClick(View view, int position) {
                    Movie movie = adapter.getItemAtPosition(position);
                    Intent replyIntent = new Intent();
                    replyIntent.putExtra(EXTRA_TITLE, movie.getTitle());
                    replyIntent.putExtra(EXTRA_RELEASE_DATE, movie.getReleaseYearFromDate());
                    replyIntent.putExtra(EXTRA_POSTER_PATH, movie.getPosterPath());
                    Log.d(TAG, "onItemClick: " + movie.getPosterPath());
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            };

    public interface RecyclerItemListener {
        void onItemClick(View v, int position);
    }
}
