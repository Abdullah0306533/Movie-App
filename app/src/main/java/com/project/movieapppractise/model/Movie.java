package com.project.movieapppractise.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Movie extends BaseObservable {
    // Fields annotated with @SerializedName map the JSON keys to Java fields
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    // Default constructor
    public Movie() {
    }

    // Getters and setters for accessing private fields
    @Bindable
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Bindable
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Bindable
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Bindable
    public Double getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    // Method for loading an image using Glide library, annotated with @BindingAdapter
    @BindingAdapter("posterPath")
    public static void loadImage(ImageView imageView, String imageURL) {
        // Constructing the full image path using the base URL and the provided imageURL
        String imagePath = "https://image.tmdb.org/t/p/w500/" + imageURL;

        // Using Glide library to load the image into the provided ImageView
        Glide.with(imageView.getContext())
                .load(imagePath)
                .into(imageView);
    }
}

