package com.iti.myapplication.model;

import com.iti.myapplication.network.RetrofitClient;

import retrofit2.Call;

public class RemoteDataSource {

  public Call<TmdbResponse> searchResults(String query) {
    return RetrofitClient
        .getInstance()
        .getMoviesApi()
        .searchMovie(RetrofitClient.API_KEY, query);
  }
}
