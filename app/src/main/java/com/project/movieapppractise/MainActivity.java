package com.project.movieapppractise;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.project.movieapppractise.databinding.ActivityMainBinding;
import com.project.movieapppractise.model.Movie;
import com.project.movieapppractise.view.MovieAdapter;
import com.project.movieapppractise.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movieArrayList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MovieAdapter movieAdapter;
    private ActivityMainBinding activityMainBinding;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set up window insets to handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.SwipeRefreshLayout),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                }
        );

        // Set up data binding
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        getPopularMovies();

        // Set up SwipeRefreshLayout
        swipeRefreshLayout = activityMainBinding.SwipeRefreshLayout;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.black));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    /**
     * Fetches popular movies and observes changes in the movie list.
     */
    private void getPopularMovies() {
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movieArrayList = (ArrayList<Movie>) moviesFromLiveData;
                displayMoviesInRecyclerView();
            }
        });
    }

    /**
     * Displays movies in the RecyclerView using the MovieAdapter.
     */
    private void displayMoviesInRecyclerView() {
        recyclerView = activityMainBinding.recyclerview;
        movieAdapter = new MovieAdapter(this, movieArrayList);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        movieAdapter.notifyDataSetChanged();
    }
}
