package com.moyehics.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moyehics.movieapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: MovieRepository
):ViewModel() {
    val _movieCategories = MutableLiveData<List<String>>()
    val movieCategories:LiveData<List<String>>
            get()=_movieCategories
    fun getMovieCategories(){
        _movieCategories.postValue(repository.getMovieCategories())
    }
}