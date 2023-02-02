package com.moyehics.movieapp.data.repository

interface MovieRepository {
    fun getMovieCategories():List<String>
}