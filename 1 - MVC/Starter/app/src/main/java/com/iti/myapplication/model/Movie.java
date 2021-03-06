package com.iti.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {
  public Movie(String title, String posterPath, String releaseDate) {
    this.title = title;
    this.posterPath = posterPath;
    this.releaseDate = releaseDate;
  }

  @SerializedName("vote_count")
  @Expose
  private Integer voteCount;

  @SerializedName("id")
  @Expose
  @PrimaryKey
  private Integer id;

  @SerializedName("video")
  @Expose
  private Boolean video;

  @SerializedName("vote_average")
  @Expose
  private Float voteAverage;

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("popularity")
  @Expose
  private Float popularity;

  @SerializedName("poster_path")
  @Expose
  private String posterPath;

  @SerializedName("original_language")
  @Expose
  private String originalLanguage;

  @SerializedName("original_title")
  @Expose
  private String originalTitle;

  @SerializedName("genre_ids")
  @Expose
  private List<Integer> genreIds;

  @SerializedName("backdrop_path")
  @Expose
  private String backdropPath;

  @SerializedName("adult")
  @Expose
  private Boolean adult;

  @SerializedName("overview")
  @Expose
  private String overview;

  @SerializedName("release_date")
  @Expose
  private String releaseDate;

  private boolean watched;

  public final String getReleaseYearFromDate() {
    return this.releaseDate.split("-")[0];
  }

  public Integer getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(Integer voteCount) {
    this.voteCount = voteCount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getVideo() {
    return video;
  }

  public void setVideo(Boolean video) {
    this.video = video;
  }

  public Float getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(Float voteAverage) {
    this.voteAverage = voteAverage;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Float getPopularity() {
    return popularity;
  }

  public void setPopularity(Float popularity) {
    this.popularity = popularity;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public void setOriginalTitle(String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public List<Integer> getGenreIds() {
    return genreIds;
  }

  public void setGenreIds(List<Integer> genreIds) {
    this.genreIds = genreIds;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public Boolean getAdult() {
    return adult;
  }

  public void setAdult(Boolean adult) {
    this.adult = adult;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public boolean isWatched() {
    return watched;
  }

  public void setWatched(boolean watched) {
    this.watched = watched;
  }
}
