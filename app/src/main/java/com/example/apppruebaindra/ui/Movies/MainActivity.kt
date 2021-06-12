package com.example.apppruebaindra.ui.Movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.apppruebaindra.R
import com.example.apppruebaindra.ui.Movies.viewmodel.MoviesViewModel
import com.example.apppruebaindra.utils.LogUtils
import com.example.entity.ResultsMovies
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private val moviesViewModel: MoviesViewModel by viewModel(clazz = MoviesViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        moviesViewModel.getMovies("1", "f46b58478f489737ad5a4651a4b25079")
    }

    fun setupViewModel() {
        moviesViewModel.moviesLiveData.observe(this, onMoviesLiveDataObserver)
        moviesViewModel._onMessageError.observe(this, onMessageErrorObserver)
        moviesViewModel._isViewLoading.observe(this, onLoadingObserver)
    }

    private val onMoviesLiveDataObserver = Observer<List<ResultsMovies>> {
        LogUtils().v(TAG, "onDetailBeach:: $it")
    }

    private val onMessageErrorObserver = Observer<Any> {
        //LogUtils().v(TAG, " onMessageErrorObserver: ${it.toString()}")
    }

    private val onLoadingObserver = Observer<Boolean> {
        /*if (it) {
            vLoading.visibility = View.VISIBLE
        } else {
            vLoading.visibility = View.GONE
        }*/
    }


}