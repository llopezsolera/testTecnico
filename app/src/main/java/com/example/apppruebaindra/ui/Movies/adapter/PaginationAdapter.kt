package com.example.apppruebaindra.ui.Movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppruebaindra.R
import com.example.entity.ResultsMovies
import java.util.*

class PaginationAdapter(private val context: Context, var onSelect: ((ResultsMovies, ImageView) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movieList: MutableList<ResultsMovies>?
    private var isLoadingAdded = false
    fun setMovieList(movieList: MutableList<ResultsMovies>?) {
        this.movieList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM -> {
                val viewItem = inflater.inflate(R.layout.item_list, parent, false)
                viewHolder = MovieViewHolder(viewItem)
            }
            LOADING -> {
                val viewLoading = inflater.inflate(R.layout.item_progress, parent, false)
                viewHolder = LoadingViewHolder(viewLoading)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var item = movieList!![position]
        when (getItemViewType(position)) {
            ITEM -> {
                val movieViewHolder = holder as MovieViewHolder
                movieViewHolder.tviTileMovie.text = item.title
                movieViewHolder.tviDescriptionMovie.text = item.overview
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
                    .placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
                    .into(movieViewHolder.iviMovie)

                //ViewCompat.setTransitionName(holder.iviMovie, animalItem.name);
                movieViewHolder.claContent.setOnClickListener {
                    onSelect?.invoke(item, movieViewHolder.iviMovie)

                }
            }
            LOADING -> {
                val loadingViewHolder = holder as LoadingViewHolder
                loadingViewHolder.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return if (movieList == null) 0 else movieList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == movieList!!.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(ResultsMovies())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        val position = movieList!!.size - 1
        val result = getItem(position)
        if (result != null) {
            movieList!!.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun add(movie: ResultsMovies) {
        movieList!!.add(movie)
        notifyItemInserted(movieList!!.size - 1)
    }

    fun addAll(moveResults: List<ResultsMovies>) {
        for (result in moveResults) {
            add(result)
        }
    }

    fun getItem(position: Int): ResultsMovies {
        return movieList!![position]
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val claContent: ConstraintLayout = itemView.findViewById<View>(R.id.claContent) as ConstraintLayout
        val tviTileMovie: TextView = itemView.findViewById<View>(R.id.tviTileMovie) as TextView
        val tviDescriptionMovie: TextView = itemView.findViewById<View>(R.id.tviDescriptionMovie) as TextView
        val iviMovie: ImageView = itemView.findViewById<View>(R.id.iviMovie) as ImageView

    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById<View>(R.id.progressBar) as ProgressBar

    }

    companion object {
        private const val LOADING = 0
        private const val ITEM = 1
    }

    init {
        movieList = LinkedList()
    }
}