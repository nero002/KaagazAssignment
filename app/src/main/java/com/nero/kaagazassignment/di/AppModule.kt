package com.nero.kaagazassignment.di

import android.app.Application
import androidx.room.Room
import com.nero.kaagazassignment.model.PhotoDatabase
import com.nero.kaagazassignment.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun providesRoomDataBase(app: Application): PhotoDatabase {
        return Room.databaseBuilder(app, PhotoDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}