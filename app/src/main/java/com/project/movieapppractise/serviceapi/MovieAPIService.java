package com.project.movieapppractise.serviceapi;

import com.project.movieapppractise.model.Result; // Importing the Result class

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Interface for Movie API Service
public interface MovieAPIService {
    // Method to get popular movies
    @GET("movie/populor") // API endpoint for popular movies
    Call<Result> getPopulorMoies(@Query("api_key") String apiKey); // Query parameter for API key
}

