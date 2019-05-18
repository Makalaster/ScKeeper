package com.makalaster.data.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.makalaster.data.models.Player

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayers: LiveData<List<Player>> = playerDao.getAllPlayers()

    @WorkerThread
    suspend fun insertPlayer(player: Player) {
        playerDao.addPlayer(player)
    }

    @WorkerThread
    suspend fun clearPlayers() {
        playerDao.clearPlayers()
    }

    fun removePlayer(player: Player) {
        playerDao.removePlayer(player)
    }
}