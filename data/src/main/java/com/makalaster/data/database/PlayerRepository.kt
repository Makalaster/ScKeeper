package com.makalaster.data.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.makalaster.data.models.Player

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayers: LiveData<List<Player>> = playerDao.getAllPlayers()

    @WorkerThread
    fun insertPlayer(player: Player) {
        playerDao.addPlayer(player)
    }

    @WorkerThread
    fun clearPlayers() {
        playerDao.clearPlayers()

        //playerDao.addPlayer(Player(0,""))
    }

    fun removePlayer(player: Player) {
        playerDao.removePlayer(player)
    }

    @WorkerThread
    fun getPlayersNotLive(): List<Player> {
        return playerDao.getAllPlayersNotLive()
    }

    fun getPlayer(id: Int): Player {
        return playerDao.getPlayer(id)
    }

    fun updatePlayer(player: Player) {
        playerDao.updatePlayer(player)
    }
}