package com.example.entity

import java.io.Serializable

data class ResultsMovies(
    var adult: Boolean? = false,
    var backdrop_path: String? = "",
    var genre_ids: List<Int>? = arrayListOf(),
    var id: Int? = 0,
    var original_language: String? = "",
    var original_title: String? = "",
    var overview: String? = "",
    var popularity: Int? = 0,
    var poster_path: String? = "",
    var release_date: String? = "",
    var title: String? = "",
    var video: Boolean? = false,
    var vote_average: Int? = 0,
    var vote_count: Int? = 0
): Serializable {

}