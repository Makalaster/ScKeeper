package com.makalaster.data.database

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.makalaster.data.models.Player
import com.makalaster.data.models.Round

class RoundRepository(private val roundDao: RoundDao) {
    val tag = RoundRepository::class.java.simpleName

    val allRounds: LiveData<List<Round>> = roundDao.getAllRounds()

    @WorkerThread
    fun insertRound(round: Round) {
        roundDao.addRound(round)
    }

    @WorkerThread
    fun clearRounds() {
        roundDao.clearRounds()

        insertRound(Round(0))
    }

    @WorkerThread
    fun getRoundsNotLive(): List<Round> {
        return roundDao.getRoundsNotLive()
    }

    @WorkerThread
    fun getRoundNotLive(roundNumber: Int): Round {
        return roundDao.getRoundNotLive(roundNumber)
    }

    @WorkerThread
    fun getRound(roundNumber: Int): LiveData<Round> {
        return roundDao.getRound(roundNumber)
    }

    fun removeRound(round: Round) {
        roundDao.removeRound(round)
    }

    fun updateRound(round: Round) {
        roundDao.updateRound(round)
    }

    fun addPlayerForAllRounds(player: Player) {
        for (round: Round in getRoundsNotLive()) {
            round.scores[player.playerNumber] = 0

            updateRound(round)
        }
    }
}