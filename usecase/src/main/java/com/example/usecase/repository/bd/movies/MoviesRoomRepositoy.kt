package com.example.usecase.repository.bd.movies

import com.example.entity.DataMovies
import com.example.entity.ResultsMovies
import com.example.usecase.repository.network.OperationResult

/**
 * Created by Luis Lopez on 6/13/21.
 * Solera Mobile
 * Peru, Lima.
 */
interface MoviesRoomRepositoy {
    suspend fun addMovies(movies: ResultsMovies): OperationResult<ResultsMovies>

}