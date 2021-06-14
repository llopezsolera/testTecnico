package com.example.repository.bd.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.repository.bd.model.ResultMovieRoom

/**
 * Created by Luis Lopez on 6/13/21.
 * Solera Mobile
 * Peru, Lima.
 */

@Dao
interface ResultMovieDao {
    @Query("SELECT * FROM ResultMovieRoom")
    fun getAll(): List<ResultMovieDao>

    @Query("SELECT * FROM ResultMovieRoom WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<ResultMovieDao>

    @Insert
    fun insertAll(vararg movies: ResultMovieRoom)

    @Delete
    fun delete(user: ResultMovieDao)
}