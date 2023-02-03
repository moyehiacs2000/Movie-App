package com.moyehics.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moyehics.movieapp.data.repository.MovieRepository
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: MovieRepository
):ViewModel() {
    fun getMovieCategories()=repository.getMovieCategories()

    fun insertMovieCategory(movieCategory: MovieCategory){
        viewModelScope.launch {
            repository.insertMovieCategory(movieCategory)
        }
    }
    fun getMoviesWithSpecificCategory(movieCategoryName:String)=repository.getMoviesWithMovieCategory(movieCategoryName)
    fun insertMovie(movie: Movie){
        viewModelScope.launch {
            repository.insertMovie(movie)
        }
    }
    fun deleteMovie(movie: Movie){
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }

}