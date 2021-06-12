package com.example.repository.network.api

import com.example.entity.DataMovies
import com.example.repository.network.AppApi
import com.example.repository.network.model.response.DataMoviesResponse
import com.example.usecase.repository.network.Constants
import com.example.usecase.repository.network.NetworkMessage
import com.example.usecase.repository.network.OperationResult
import com.example.usecase.repository.network.movies.MoviesNetworkRepository

class MoviesNetwork(private val appApi: AppApi) : MoviesNetworkRepository {
    override suspend fun getMovies(page: String, api_key: String): OperationResult<DataMovies> {
        try {
            val response =  appApi.getMovies(
                page,
                api_key
            )
            response?.let {
                return if (response.raw().code() == Constants.RESPONSE_SERVICE_OK && it.isSuccessful && it.body() != null) {
                    val data = it.body()
                    OperationResult.Success(DataMoviesResponse.toDataMovies(data as DataMoviesResponse))
                } else {
                    val message = "Error"
                    OperationResult.Error(NetworkMessage(message ?: "", 0))

                }
            }
        } catch (e: Exception) {
            return OperationResult.Error(NetworkMessage("", 0))
        }
    }

}