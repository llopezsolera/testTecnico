package com.example.apppruebaindra.ui.Movies

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.Fade
import android.transition.TransitionSet
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.transition.doOnEnd
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.apppruebaindra.R
import com.example.apppruebaindra.application.AppApplication
import com.example.apppruebaindra.databinding.ActivityDetailMovie2Binding
import com.example.apppruebaindra.ui.Movies.viewmodel.MoviesViewModel
import com.example.apppruebaindra.utils.Constans.EXTRA_ANIMAL_IMAGE_TRANSITION_NAME
import com.example.apppruebaindra.utils.Constans.MOVIE
import com.example.apppruebaindra.utils.load
import com.example.entity.ResultsMovies
import com.example.repository.bd.dao.ResultMovieDao
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movie2.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovie2Activity : AppCompatActivity() {
    var movie = ResultsMovies()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie2)
        val binding: ActivityDetailMovie2Binding = DataBindingUtil.setContentView(
            this, R.layout.activity_detail_movie2)
        initUI()
        binding.item = movie

    }

    fun initUI() {
        intent.getStringExtra(EXTRA_ANIMAL_IMAGE_TRANSITION_NAME)?.let {
            iviDetailMovie.transitionName = it

        }
        intent.getSerializableExtra(MOVIE)?.let {
            movie = it as ResultsMovies

        }
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = movie.title

        supportStartPostponedEnterTransition()
        iviDetailMovie.load("https://image.tmdb.org/t/p/w500${movie.poster_path}", true) {
            supportStartPostponedEnterTransition()
        }

        window.sharedElementEnterTransition = TransitionSet()
            .addTransition(ChangeImageTransform())
            .addTransition(ChangeBounds())
            .apply {
                doOnEnd { iviDetailMovie.load("https://image.tmdb.org/t/p/w500${movie.poster_path}") }
            }

        window.enterTransition = Fade().apply {
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
            excludeTarget(R.id.action_bar_container, true)
        }
    }

}