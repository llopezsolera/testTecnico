package com.example.usecase.repository.network.movies

import com.example.entity.DataMovies
import com.example.usecase.repository.network.OperationResult

interface MoviesNetworkRepository {
    suspend fun getMovies(page: String, apiKey: String): OperationResult<DataMovies>

}