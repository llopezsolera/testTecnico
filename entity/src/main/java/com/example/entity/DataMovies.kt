package com.example.entity

import java.io.Serializable

data class DataMovies(
    var page: Int? = 0,
    var results: List<ResultsMovies>? = arrayListOf(),
    var total_pages: Int? = 0,
    var total_results: Int? = 0
) : Serializable {
}