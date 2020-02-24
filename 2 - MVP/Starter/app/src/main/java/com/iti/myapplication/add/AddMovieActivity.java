package com.iti.myapplication.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.iti.myapplication.R;
import com.iti.myapplication.model.LocalDataSource;
import com.iti.myapplication.search.SearchActivity;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.iti.myapplication.network.RetrofitClient.TMDB_IMAGEURL;

public class AddMovieActivity extends AppCompatActivity implements AddMovieContract.ViewInterface {

  private EditText titleEditText;
  private EditText releaseDateEditText;
  private ImageView movieImageView;
  private LocalDataSource dataSource;
  public static final int SEARCH_MOVIE_ACTIVITY_REQUEST_CODE = 2;
  private AddMovieContract.PresenterInterface addMoviePresenter;


}
