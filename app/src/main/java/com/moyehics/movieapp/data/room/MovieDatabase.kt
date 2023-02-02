package com.moyehics.movieapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory

@Database(entities = [
    Movie::class,
    MovieCategory::class
],
version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao() : MovieDao
}