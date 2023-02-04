package com.moyehics.movieapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Movie (
        // make Movie movie ID Primary Key
        // Because the user can modify the name of the movie
        @PrimaryKey(autoGenerate = true)
        var movieID:Int=0,
        var name:String,
        var description:String,
        val movieCategoryName : String
        ):Serializable