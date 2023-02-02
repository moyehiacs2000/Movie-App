package com.moyehics.movieapp.data.repository

import com.moyehics.movieapp.data.room.MovieDao
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.data.room.entities.relations.MovieCategoryWithMovies

class MovieRepositoryImp(
    private val movieDao: MovieDao
):MovieRepository {
    override suspend fun getMovieCategories(): List<MovieCategory> {
        return movieDao.getAllCategories();
    }

    override suspend fun getMoviesWithMovieCategory(movieCategoryID: Int): List<MovieCategoryWithMovies> {
        return movieDao.getMovieCategoryWithMovies(movieCategoryID);
    }

    override suspend fun insertMovieCategory(movieCategory: MovieCategory) {
        movieDao.insertMovieCategory(movieCategory)
    }

    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

}