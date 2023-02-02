package com.moyehics.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moyehics.movieapp.data.repository.MovieRepository
import com.moyehics.movieapp.data.room.entities.MovieCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: MovieRepository
):ViewModel() {
    val _movieCategories = MutableLiveData<List<MovieCategory>>()
    val movieCategories:LiveData<List<MovieCategory>>
            get()=_movieCategories
    fun getMovieCategories(){
        viewModelScope.launch {
            _movieCategories.value=repository.getMovieCategories()
        }
    }

    fun insertMovieCategory(movieCategory: MovieCategory){
        viewModelScope.launch {
            repository.insertMovieCategory(movieCategory)
        }
    }


}