package com.project.movieapppractise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.project.movieapppractise.R;
import com.project.movieapppractise.databinding.MovieListItemBinding;
import com.project.movieapppractise.model.Movie;

import java.util.ArrayList;

/**
 * Adapter for displaying a list of movies in a RecyclerView.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movieArrayList;

    /**
     * Constructor for MovieAdapter.
     *
     * @param context       The context in which the adapter is used.
     * @param movieArrayList The list of movies to be displayed.
     */
    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        this.context = context;
        this.movieArrayList = movieArrayList;
    }

    /**
     * Creates a new ViewHolder for a movie item.
     *
     * @param parent   The parent ViewGroup.
     * @param viewType The type of the view.
     * @return A new MovieViewHolder.
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_item,
                parent,
                false
        );
        return new MovieViewHolder(movieListItemBinding);
    }

    /**
     * Binds the movie data to the ViewHolder.
     *
     * @param holder   The ViewHolder to bind the data to.
     * @param position The position of the item in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieArrayList.get(position);
        holder.movieListItemBinding.setMovie(movie);
    }

    /**
     * Returns the total number of items in the list.
     *
     * @return The number of items in the list.
     */
    @Override
    public int getItemCount() {
        return movieArrayList != null ? movieArrayList.size() : 0;
    }

    /**
     * ViewHolder class for movie items.
     */
    class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieListItemBinding movieListItemBinding;

        /**
         * Constructor for MovieViewHolder.
         *
         * @param movieListItemBinding The DataBinding object for the movie item layout.
         */
        public MovieViewHolder(MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            // Set an onClickListener for the root view of the ViewHolder
            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click event
                }
            });
        }
    }
}
