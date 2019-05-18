package com.makalaster.data.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.makalaster.data.models.Round

class RoundRepository(private val roundDao: RoundDao) {
    val allRounds: LiveData<List<Round>> = roundDao.getAllRounds()

    @WorkerThread
    suspend fun insertRound(round: Round) {
        roundDao.addRound(round)
    }

    @WorkerThread
    suspend fun clearRounds() {
        roundDao.clearRounds()
    }

    fun removeRound(round: Round) {
        roundDao.removeRound(round)
    }
}