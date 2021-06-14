package com.example.repository.bd.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Luis Lopez on 6/13/21.
 * Solera Mobile
 * Peru, Lima.
 */

@Entity
data class ResultMovieRoom(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = 0,
    var adult: Boolean? = false,
    var backdrop_path: String? = "",
    var genre_ids: List<Int>? = arrayListOf(),
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
) {
}