package com.moyehics.movieapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Movie (
        @PrimaryKey(autoGenerate = true)
        var movieID:Int=0,
        var name:String,
        var description:String,
        val movieCategoryID : Int
        ):Serializable