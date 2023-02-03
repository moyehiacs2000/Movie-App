package com.moyehics.movieapp.di

import android.content.Context
import androidx.room.Room
import com.moyehics.movieapp.data.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext app:Context
    )= Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        "movie_db"
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db:MovieDatabase)=db.getMovieDao()

}