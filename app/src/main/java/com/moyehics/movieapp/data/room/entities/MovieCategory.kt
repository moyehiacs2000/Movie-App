package com.moyehics.movieapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCategory(
    @PrimaryKey(autoGenerate = false)
    val movieCategoryName : String
)