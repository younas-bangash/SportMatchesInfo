package com.sport.matchesinfo.data.local

import androidx.room.*
import com.sport.matchesinfo.data.MatchDetails

/**
 * Interface for the Database operations
 */
@Dao
public interface MatchDetailsDao {
    @Query("SELECT * FROM matchDetails")
    fun getAll(): List<MatchDetails>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MatchDetails>)

    @Query("DELETE FROM matchDetails WHERE firstTeamName = :firstTeamName AND secondTeamName = :secondTeamName")
    fun deleteRow(firstTeamName: String, secondTeamName: String)
}