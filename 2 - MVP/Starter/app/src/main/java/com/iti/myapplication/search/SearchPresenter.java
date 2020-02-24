package com.iti.myapplication.search;

import android.app.Application;

import com.iti.myapplication.model.RemoteDataSource;
import com.iti.myapplication.model.TmdbResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.PresenterInterface {
    private final String TAG = "SearchPresenter";

    private SearchContract.ViewInterface viewInterface;
    private Application application;
    private RemoteDataSource dataSource;

    public SearchPresenter(SearchContract.ViewInterface viewInterface, RemoteDataSource dataSource) {
        this.viewInterface = viewInterface;
        this.dataSource = dataSource;
    }

    @Override
    public void getSearchResults(String query) {
        dataSource
                .search(query)
                .enqueue(
                        new Callback<TmdbResponse>() {
                            @Override
                            public void onResponse(Call<TmdbResponse> call, Response<TmdbResponse> response) {
                                if (response.isSuccessful()) {
                                    viewInterface.displayResult(response.body());
                                }
                            }

                            @Override
                            public void onFailure(Call<TmdbResponse> call, Throwable t) {
                                viewInterface.displayError("Error fetching Movie Data");
                            }
                        });
    }

    @Override
    public void stop() {

    }
}
