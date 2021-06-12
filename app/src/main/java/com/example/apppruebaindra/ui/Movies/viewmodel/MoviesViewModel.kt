package com.example.apppruebaindra.ui.Movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entity.DataMovies
import com.example.entity.ResultsMovies
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