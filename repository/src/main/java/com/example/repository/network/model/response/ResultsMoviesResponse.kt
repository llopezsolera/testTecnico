package com.example.repository.network.model.response

import com.example.entity.ResultsMovies
import java.io.Serializable

data class ResultsMoviesResponse (
    var adult: Boolean? = false,
    var backdrop_path: String? = "",
    //var genre_ids: List<Int>? = arrayListOf(),
    var id: Int? = 0,
    var original_language: String? = "",
    var original_title: String? = "",
    var overview: String? = "",
    var popularity: Double? = 0.0,
    var poster_path: String? = "",
    var release_date: String? = "",
    var title: String? = "",
    var video: Boolean? = false,
    var vote_average: Double? = 0.0,
    var vote_count: Int? = 0
) : Serializable {
    companion object {
        fun toResultsMovies(response: ResultsMoviesResponse) : ResultsMovies {
            return ResultsMovies(
                adult = response.adult,
                backdrop_path = response.backdrop_path,
                //genre_ids = response.genre_ids,
                id = response.id,
                original_language = response.original_language,
                original_title = response.original_title,
                overview = response.overview,
                popularity = response.popularity,
                poster_path = response.poster_path,
                release_date = response.release_date,
                title = response.title,
                video = response.video,
                vote_average = response.vote_average,
                vote_count = response.vote_count
            )
        }

        fun toList(responses: List<ResultsMoviesResponse>): List<ResultsMovies> {
            val list: ArrayList<ResultsMovies> = ArrayList()
            for (item in responses) {
                list.add(toResultsMovies(item))
            }
            return list
        }
    }
}