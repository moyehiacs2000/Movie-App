package com.moyehics.movieapp.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieCategory(
    // make Movie Category Name Primary Key
    // To prevent the user from creating a new category with the same name
    @PrimaryKey(autoGenerate = false)
    val movieCategoryName : String
)