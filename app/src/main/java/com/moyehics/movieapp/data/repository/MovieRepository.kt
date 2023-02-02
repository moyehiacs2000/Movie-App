package com.moyehics.movieapp.data.repository

import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.data.room.entities.relations.MovieCategoryWithMovies

interface MovieRepository {
    suspend fun getMovieCategories():List<MovieCategory>
    suspend fun getMoviesWithMovieCategory(movieCategoryID:Int):List<MovieCategoryWithMovies>
    suspend fun insertMovieCategory(movieCategory: MovieCategory)
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}