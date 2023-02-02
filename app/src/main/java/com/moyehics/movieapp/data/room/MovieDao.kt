package com.moyehics.movieapp.data.room

import androidx.room.*
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.data.room.entities.relations.MovieCategoryWithMovies
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieCategory(movieCategory: MovieCategory)

    @Query("select * from moviecategory")
    suspend fun getAllCategories():List<MovieCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie:Movie)

    @Transaction
    @Query("Select * from moviecategory where movieCategoryID=:movieCategoryID")
    suspend fun getMovieCategoryWithMovies(movieCategoryID:Int):List<MovieCategoryWithMovies>
}