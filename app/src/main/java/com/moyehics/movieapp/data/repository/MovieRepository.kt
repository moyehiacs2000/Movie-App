package com.moyehics.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.data.room.entities.relations.MovieCategoryWithMovies

interface MovieRepository {
     fun getMovieCategories(): LiveData<List<MovieCategory>>
     fun getMoviesWithMovieCategory(movieCategoryName:String):LiveData<List<MovieCategoryWithMovies>>
    suspend fun insertMovieCategory(movieCategory: MovieCategory)
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)

}