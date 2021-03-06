package com.makalaster.sckeeper.gameactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.makalaster.data.database.GameDatabase
import com.makalaster.data.database.PlayerRepository
import com.makalaster.data.database.RoundRepository
import com.makalaster.data.models.Player
import com.makalaster.data.models.Round
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val roundRepository: RoundRepository
    private val playerRepository: PlayerRepository

    val rounds: LiveData<List<Round>>

    val roundAdded: MutableLiveData<Void> = MutableLiveData()

    init {
        val roundDao = GameDatabase.getInstance(application).roundDao()
        roundRepository = RoundRepository(roundDao)

        rounds = roundRepository.allRounds

        val playerDao = GameDatabase.getInstance(application).playerDao()
        playerRepository = PlayerRepository(playerDao)
    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.clearPlayers()

            clearRounds()
        }
    }

    fun clearRounds() {
        viewModelScope.launch(Dispatchers.IO) {
            roundRepository.clearRounds()
        }
    }

    fun addRound(round: Round) {
        viewModelScope.launch(Dispatchers.IO) {
            for (player: Player in playerRepository.getPlayersNotLive()) {
                round.scores[player.playerNumber] = 0
            }

            roundRepository.insertRound(round)

            roundAdded.postValue(null)
        }
    }
}