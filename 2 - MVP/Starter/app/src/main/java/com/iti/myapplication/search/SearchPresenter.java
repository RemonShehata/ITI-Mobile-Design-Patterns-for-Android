package com.iti.myapplication.search;

import android.util.Log;

import com.iti.myapplication.model.RemoteDataSource;
import com.iti.myapplication.model.TmdbResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.PresenterInterface {
  private final String TAG = "SearchPresenter";

  SearchContract.ViewInterface viewInterface;
  RemoteDataSource dataSource;


}
