package com.project.movieapppractise.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.movieapppractise.model.Movie;
import com.project.movieapppractise.model.MovieRepository;

import java.util.List;

/**
 * ViewModel for managing movie data and providing it to the UI.
 */
public class MovieViewModel extends AndroidViewModel {
    private  MovieRepository repository;

    /**
     * Constructor for the MovieViewModel.
     *
     * @param application The application context.
     */
    public MovieViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

    /**
     * Gets a LiveData object containing a list of all movies.
     *
     * @return LiveData object containing a list of movies.
     */
    public LiveData<List<Movie>> getAllMovies() {
        return repository.getMutableLiveData();
    }
}
