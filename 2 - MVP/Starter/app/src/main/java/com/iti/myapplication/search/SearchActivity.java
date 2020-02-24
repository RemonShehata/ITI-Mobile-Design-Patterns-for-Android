package com.iti.myapplication.search;

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

import com.iti.myapplication.R;
import com.iti.myapplication.model.Movie;
import com.iti.myapplication.model.RemoteDataSource;
import com.iti.myapplication.model.TmdbResponse;

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


}
