package com.example.apppruebaindra.ui.Movies.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppruebaindra.application.AppApplication
import com.example.apppruebaindra.ui.Movies.MainActivity.Companion.TOTAL_PAGES
import com.example.apppruebaindra.utils.LogUtils
import com.example.entity.DataMovies
import com.example.entity.ResultsMovies
import com.example.repository.bd.MoviesDB
import com.example.repository.bd.dao.ResultMovieDao
import com.example.repository.bd.model.ResultMovieRoom
import com.example.usecase.repository.network.OperationResult
import com.example.usecase.usecases.MoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {
    private val TAG = javaClass.simpleName



    val _isViewLoading = MutableLiveData<Boolean>()

    val _isEmptyList = MutableLiveData<Boolean>()

    val _onMessageError = MutableLiveData<Any>()

    val moviesLiveData = MutableLiveData<List<ResultsMovies>>()



    //
    var loginDatabase: MoviesDB? = null



    fun getMovies(page: String, apiKey: String) {
        _isViewLoading.postValue(true)
        viewModelScope.launch {
            var result: OperationResult<DataMovies> = withContext(Dispatchers.IO) {
                moviesUseCase.getMovies(page, apiKey)
            }
            _isViewLoading.postValue(false)
            when (result) {
                is OperationResult.Success -> {
                    if (result.data?.results.isNullOrEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        TOTAL_PAGES =  result?.data?.total_pages ?: 0
                        moviesLiveData.value = result?.data?.results ?: arrayListOf()
                    }
                }
                is OperationResult.Error -> {
                    _onMessageError.postValue(result?.exception ?: null)
                }
            }
        }
    }

}