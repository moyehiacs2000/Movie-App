package com.moyehics.movieapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import com.moyehics.movieapp.data.room.entities.relations.MovieCategoryWithMovies
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieCategory(movieCategory: MovieCategory)

    @Query("select * from moviecategory order by movieCategoryName")
    fun getAllCategories(): LiveData<List<MovieCategory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie:Movie)

    /*
    * adding transaction that will just make sure that there can't be any multi-threading issues here with this function
    */
    @Transaction
    @Query("Select * from moviecategory where movieCategoryName=:movieCategoryName")
     fun getMovieCategoryWithMovies(movieCategoryName:String):LiveData<List<MovieCategoryWithMovies>>
}