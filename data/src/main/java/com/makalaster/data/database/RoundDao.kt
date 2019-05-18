package com.makalaster.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.makalaster.data.models.Round

@Dao
interface RoundDao {

    @Insert
    fun addRound(round: Round)

    @Query("SELECT * FROM ROUNDS ORDER BY round_number ASC")
    fun getAllRounds(): LiveData<List<Round>>

    @Query("DELETE FROM " + Round.TABLE_NAME)
    fun clearRounds()

    @Delete
    fun removeRound(round: Round)
}