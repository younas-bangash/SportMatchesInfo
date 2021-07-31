package com.sport.matchesinfo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sport.matchesinfo.data.MatchDetails

/**
 * App Database class for offline support
 */
@Database(entities = [MatchDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDetailsDao(): MatchDetailsDao
}