package com.moyehics.movieapp.data.repository

import androidx.lifecycle.LiveData
import com.moyehics.movieapp.data.room.MovieDao
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.data.room.entities.relations.MovieCategoryWithMovies

class MovieRepositoryImp(
    private val movieDao: MovieDao
):MovieRepository {
    override  fun getMovieCategories(): LiveData<List<MovieCategory>> {
        return movieDao.getAllCategories();
    }

    override  fun getMoviesWithMovieCategory(movieCategoryName: String): LiveData<List<MovieCategoryWithMovies>> {
        return movieDao.getMovieCategoryWithMovies(movieCategoryName);
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