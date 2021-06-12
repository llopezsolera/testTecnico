package com.example.usecase.usecases

import com.example.usecase.repository.network.movies.MoviesNetworkRepository

class MoviesUseCase(private val moviesNetworkRepository: MoviesNetworkRepository) {
    suspend fun getMovies(page: String, apiKey: String) = moviesNetworkRepository.getMovies(page, apiKey)
}