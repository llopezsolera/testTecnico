package com.example.apppruebaindra.ui.Movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.apppruebaindra.R;
import com.example.entity.ResultsMovies;

import java.util.LinkedList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ResultsMovies> movieList;
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;

    public PaginationAdapter(Context context) {
        this.context = context;
        movieList = new LinkedList<>();
    }

    public void setMovieList(List<ResultsMovies> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.item_list, parent, false);
                viewHolder = new MovieViewHolder(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(viewLoading);
                break;
        }
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ResultsMovies movie = movieList.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
                movieViewHolder.tviTileMovie.setText(movie.getTitle());
                movieViewHolder.tviDescriptionMovie.setText(movie.getOverview());
                Glide.with(context)
                        .load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path())
                        .placeholder(R.drawable.ic_place_holder)
                        .error(R.drawable.ic_place_holder)
                        .into(movieViewHolder.iviMovie);
                break;

            case LOADING:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movieList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new ResultsMovies());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieList.size() - 1;
        ResultsMovies result = getItem(position);

        if (result != null) {
            movieList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void add(ResultsMovies movie) {
        movieList.add(movie);
        notifyItemInserted(movieList.size() - 1);
    }

    public void addAll(List<ResultsMovies> moveResults) {
        for (ResultsMovies result : moveResults) {
            add(result);
        }
    }

    public ResultsMovies getItem(int position) {
        return movieList.get(position);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView tviTileMovie;
        private TextView tviDescriptionMovie;
        private ImageView iviMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            tviTileMovie = (TextView) itemView.findViewById(R.id.tviTileMovie);
            tviDescriptionMovie = (TextView) itemView.findViewById(R.id.tviDescriptionMovie);
            iviMovie = (ImageView) itemView.findViewById(R.id.iviMovie);
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);

        }
    }

}
