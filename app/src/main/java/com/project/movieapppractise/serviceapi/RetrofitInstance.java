package com.project.movieapppractise.serviceapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    // Retrofit instance for handling network requests
    private static Retrofit retrofit = null;

    // Base URL of the API
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    // Method to get an instance of the MovieAPIService interface
    public static MovieAPIService getService() {
        // Create Retrofit instance if it's not already initialized
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    // Set the base URL for the Retrofit instance
                    .baseUrl(BASE_URL)
                    // Add Gson converter factory to parse JSON responses
                    .addConverterFactory(GsonConverterFactory.create())
                    // Build the Retrofit instance
                    .build();
        }
        // Return an implementation of the MovieAPIService interface
        return retrofit.create(MovieAPIService.class);
    }
}

