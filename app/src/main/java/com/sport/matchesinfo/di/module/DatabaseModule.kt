package com.sport.matchesinfo.di.module

import android.content.Context
import androidx.room.Room
import com.sport.matchesinfo.data.local.AppDatabase
import com.sport.matchesinfo.data.local.MatchDetailsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Database module
 */

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MatchDetailsDao {
        return appDatabase.matchDetailsDao()
    }
}