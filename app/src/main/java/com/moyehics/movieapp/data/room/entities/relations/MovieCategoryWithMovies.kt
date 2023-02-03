package com.moyehics.movieapp.data.room.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.moyehics.movieapp.data.room.entities.Movie
import com.moyehics.movieapp.data.room.entities.MovieCategory

data class MovieCategoryWithMovies(
    @Embedded val movieCategory: MovieCategory,
    @Relation(
        parentColumn = "movieCategoryName",
        entityColumn = "movieCategoryName"
    )
    val movies:List<Movie>
)