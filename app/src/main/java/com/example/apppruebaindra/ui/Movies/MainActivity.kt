package com.example.apppruebaindra.ui.Movies

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppruebaindra.R
import com.example.apppruebaindra.ui.Movies.adapter.PaginationAdapter
import com.example.apppruebaindra.ui.Movies.adapter.PaginationScrollListener
import com.example.apppruebaindra.ui.Movies.viewmodel.MoviesViewModel
import com.example.apppruebaindra.utils.BaseAppCompat
import com.example.apppruebaindra.utils.Constans.EXTRA_ANIMAL_IMAGE_TRANSITION_NAME
import com.example.apppruebaindra.utils.LogUtils
import com.example.apppruebaindra.utils.hide
import com.example.apppruebaindra.utils.show
import com.example.entity.ResultsMovies
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseAppCompat() {
    private val TAG = javaClass.simpleName
    private val PAGE_START = 1
    private val moviesViewModel: MoviesViewModel by viewModel(clazz = MoviesViewModel::class)
    private var paginationAdapter = PaginationAdapter(this)

    var currentPage = PAGE_START
    var rviMovies: RecyclerView? = null


    private var _isLoading = false
    private var _isLastPage = false


    companion object {
        var TOTAL_PAGES = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        setupViewModel()
        initRecyclerView()
        moviesViewModel.getMovies(currentPage.toString(), "f46b58478f489737ad5a4651a4b25079")
    }

    fun setupViewModel() {
        moviesViewModel.moviesLiveData.observe(this, onMoviesLiveDataObserver)
        moviesViewModel._onMessageError.observe(this, onMessageErrorObserver)
        moviesViewModel._isViewLoading.observe(this, onLoadingObserver)
    }

    private val onMoviesLiveDataObserver = Observer<List<ResultsMovies>> {
        LogUtils().v(TAG, "onMoviesLiveDataObserver:: $it")
        showMovies(it)
    }

    private val onMessageErrorObserver = Observer<Any> {
        LogUtils().v(TAG, " onMessageErrorObserver: ${it.toString()}")
        viewError.show()
    }

    private val onLoadingObserver = Observer<Boolean> {
        if (it && firstPage) {
            vLoading?.show()
        } else {
            vLoading?.hide()
        }
    }

    var firstPage = true

    fun loadNextPage() {
        LogUtils().v(TAG, "loadNextPage")
        moviesViewModel.getMovies(currentPage.toString(), "f46b58478f489737ad5a4651a4b25079")
    }

    fun showMovies(movies: List<ResultsMovies>) {
        if (firstPage) {
            firstPage = false
            paginationAdapter.addAll(movies)
            if (currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter() else _isLastPage =
                true
        } else {
            paginationAdapter.removeLoadingFooter()
            _isLoading = false
            paginationAdapter.addAll(movies)
            if (currentPage != TOTAL_PAGES) paginationAdapter.addLoadingFooter() else _isLastPage =
                true
        }
        //paginationAdapter.addAll(movies)
    }

    fun initRecyclerView() {
        rviMovies = findViewById(R.id.rviMovies)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rviMovies?.layoutManager = linearLayoutManager
        rviMovies?.itemAnimator = DefaultItemAnimator()
        rviMovies?.adapter = paginationAdapter
        rviMovies?.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                _isLoading = true
                currentPage += 1
                loadNextPage()
            }

            override val getTotalPageCount: Int
                get() = TOTAL_PAGES

            override val isLastPage: Boolean
                get() = _isLastPage

            override val isLoading: Boolean
                get() = _isLoading

        })
        paginationAdapter.onSelect = { resultsMovies: ResultsMovies, imageView: ImageView ->
            var bundle = Bundle()
            bundle.putString(
                EXTRA_ANIMAL_IMAGE_TRANSITION_NAME,
                ViewCompat.getTransitionName(imageView)
            )
            bundle.putSerializable("MOVIE", resultsMovies)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                imageView,
                ViewCompat.getTransitionName(imageView)!!
            )
            nextDataTransitionAnimation(DetailMovie2Activity::class.java, bundle, options, true)
        }
    }

    fun initUI() {
        //vLoading = findViewById(R.id.vLoading)
    }

}