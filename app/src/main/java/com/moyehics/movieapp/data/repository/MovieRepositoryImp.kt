package com.moyehics.movieapp.data.repository

class MovieRepositoryImp:MovieRepository {
    override fun getMovieCategories(): List<String> {
        return arrayListOf("Action","Comedy")
    }
}