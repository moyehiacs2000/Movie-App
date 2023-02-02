package com.moyehics.movieapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
        @PrimaryKey(autoGenerate = true)
        val movieID:Int,
        val name:String,
        val description:String,
        val movieCategoryID : Int
        )