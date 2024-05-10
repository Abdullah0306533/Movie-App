package com.project.movieapppractise.model;

import android.app.Application;
import androidx.lifecycle.MutableLiveData;
import com.project.movieapppractise.R;
import com.project.movieapppractise.serviceapi.MovieAPIService;
import com.project.movieapppractise.serviceapi.RetrofitInstance;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Repository class for managing movie data
public class MovieRepository {
    // List of movies
    private ArrayList<Movie> movies = new ArrayList<>();

    // MutableLiveData to hold list of movies
    private MutableLiveData<List<Movie>> mutableLiveData;

    // Reference to the application context
    private Application application;

    // Constructor to initialize the repository with application context
    public MovieRepository(Application application) {
        this.application = application;
    }

    // Method to get MutableLiveData containing list of movies
    public MutableLiveData<List<Movie>> getMutableLiveData() {
        // Check if MutableLiveData is null, initialize if needed
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }

        // Initialize MovieAPIService using RetrofitInstance
        MovieAPIService movieAPIService = RetrofitInstance.getService();

        // Create a call to fetch popular movies
        Call<Result> call = movieAPIService.getPopulorMoies(application.getApplicationContext().getString(R.string.api_key));

        // Enqueue the call asynchronously
        call.enqueue (new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // Handle successful response
                Result result = response.body();
                if (result != null && result.getResults() != null) {
                    // Update the list of movies
                    movies = (ArrayList<Movie>) result.getResults();
                    // Update the MutableLiveData with the new list of movies
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {
                // Handle failure
            }
        });

        return mutableLiveData; // Return the MutableLiveData object
    }
}
