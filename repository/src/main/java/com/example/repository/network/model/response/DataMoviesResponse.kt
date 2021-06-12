package com.example.repository.network.model.response

import com.example.entity.DataMovies
import com.example.entity.ResultsMovies
import java.io.Serializable

data class DataMoviesResponse (
    var page: Int? = 0,
    var results: List<ResultsMoviesResponse>? = arrayListOf(),
    var total_pages: Int? = 0,
    var total_results: Int? = 0
) : Serializable {
    companion object {
        fun toDataMovies(response: DataMoviesResponse): DataMovies {
            return DataMovies(
                page = response.page,
                results = response.results?.let { ResultsMoviesResponse.toList(it) },
                total_pages = response.total_pages,
                total_results = response.total_results
            )
        }
    }

}