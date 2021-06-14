package com.example.repository.bd

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repository.bd.dao.ResultMovieDao
import com.example.repository.bd.model.ResultMovieRoom


/**
 * Created by Luis Lopez on 6/13/21.
 * Solera Mobile
 * Peru, Lima.
 */

@Database(
    entities = [ResultMovieRoom::class],
    version = 1

)
abstract class MoviesDB: RoomDatabase() {
        abstract fun movieDao() : ResultMovieDao

    /*companion object {

        @Volatile
        private var INSTANCE: MoviesDB? = null

        fun getDataseClient(context: Context) : MoviesDB {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, MoviesDB::class.java, "MOVIE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }*/

}