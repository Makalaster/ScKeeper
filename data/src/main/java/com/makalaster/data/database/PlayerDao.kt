package com.makalaster.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.makalaster.data.models.Player

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: Player)

    @Query("SELECT * FROM PLAYERS ORDER BY player_number ASC")
    fun getAllPlayers(): LiveData<List<Player>>

    @Query("DELETE FROM " + Player.TABLE_NAME)
    fun clearPlayers()

    @Delete
    fun removePlayer(player: Player)
}