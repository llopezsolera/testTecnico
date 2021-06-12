package com.example.repository.network


import com.example.repository.BuildConfig
import com.example.repository.network.model.response.DataMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {
    @GET("${BuildConfig.BASE_URL}upcoming")
    suspend fun getMovies(
        @Query("page") page: String,
        @Query("api_key") apiKey: String
    ) : Response<DataMoviesResponse>
}