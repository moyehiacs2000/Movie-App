package com.moyehics.movieapp.di

import com.moyehics.movieapp.data.repository.MovieRepository
import com.moyehics.movieapp.data.repository.MovieRepositoryImp
import com.moyehics.movieapp.data.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(movieDao: MovieDao):MovieRepository{
        return MovieRepositoryImp(movieDao)
    }
}