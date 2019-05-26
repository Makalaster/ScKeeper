package com.makalaster.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.makalaster.data.models.Round

@Dao
interface RoundDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRound(round: Round)

    @Query("SELECT * FROM ROUNDS ORDER BY round_number ASC")
    fun getAllRounds(): LiveData<List<Round>>

    @Query("SELECT * FROM rounds ORDER BY round_number ASC")
    fun getRoundsNotLive(): List<Round>

    @Query("DELETE FROM " + Round.TABLE_NAME)
    fun clearRounds()

    @Delete
    fun removeRound(round: Round)

    @Query("SELECT * FROM ROUNDS WHERE round_number = :roundNumber")
    fun getRound(roundNumber: Int): LiveData<Round>

    @Query("SELECT * FROM ROUNDS WHERE round_number = :roundNumber")
    fun getRoundNotLive(roundNumber: Int): Round

    @Update
    fun updateRound(round: Round)
}