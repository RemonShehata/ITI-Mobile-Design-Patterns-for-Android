package com.iti.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.myapplication.model.Movie;
import com.iti.myapplication.model.RemoteDataSource;
import com.iti.myapplication.model.TmdbResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SearchActivity extends AppCompatActivity {
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
    private RemoteDataSource dataSource = new RemoteDataSource();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        searchResultsRecyclerView = findViewById(R.id.search_results_recyclerview);
        noMoviesTextView = findViewById(R.id.no_movies_textview);
        progressBar = findViewById(R.id.progress_bar);

        Intent intent = getIntent();
        query = intent.getStringExtra(SEARCH_QUERY);

        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(VISIBLE);
        getSearchResults(query);
    }

    private void setupViews() {
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getSearchResults(final String query) {
        dataSource
                .searchResults(query)
                .enqueue(
                        new Callback<TmdbResponse>() {
                            @Override
                            public void onResponse(Call<TmdbResponse> call, Response<TmdbResponse> response) {
                                if (response.isSuccessful()) {
                                    displayResult(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<TmdbResponse> call, Throwable t) {
                                displayError("Error fetching Movie Data");
                            }
                        });
    }

    private void displayResult(TmdbResponse data) {
        progressBar.setVisibility(INVISIBLE);

        if (data.getTotalResults() == null || data.getTotalResults() == 0) {
            searchResultsRecyclerView.setVisibility(INVISIBLE);
            noMoviesTextView.setVisibility(VISIBLE);
        } else {
            adapter = new SearchAdapter(data.getResults(), this, itemListener);
            searchResultsRecyclerView.setAdapter(adapter);

            searchResultsRecyclerView.setVisibility(VISIBLE);
            noMoviesTextView.setVisibility(INVISIBLE);
        }
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show();
    }

    private void displayError(String string) {
        showToast(string);
    }

    private RecyclerItemListener itemListener =
            new SearchActivity.RecyclerItemListener() {
                public void onItemClick(View view, int position) {
                    Movie movie = adapter.getItemAtPosition(position);
                    Intent replyIntent = new Intent();
                    replyIntent.putExtra(EXTRA_TITLE, movie.getTitle());
                    replyIntent.putExtra(EXTRA_RELEASE_DATE, movie.getReleaseYearFromDate());
                    replyIntent.putExtra(EXTRA_POSTER_PATH, movie.getPosterPath());
                    setResult(-1, replyIntent);
                    finish();
                }
            };

    interface RecyclerItemListener {
        void onItemClick(View v, int position);
    }
}
