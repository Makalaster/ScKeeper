package com.makalaster.sckeeper.gameactivity

import android.app.Application
import androidx.lifecycle.*
import com.makalaster.data.database.GameDatabase
import com.makalaster.data.database.PlayerRepository
import com.makalaster.data.database.RoundRepository
import com.makalaster.data.models.Player
import com.makalaster.data.models.Round
import com.makalaster.widgets.ScoreBoxListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoundViewModel(application: Application, private val roundNumber: Int) : AndroidViewModel(application),
    ScoreBoxListener {
    private val playerRepository: PlayerRepository
    private var roundRepository: RoundRepository

    val players: LiveData<List<Player>>
    val round: MutableLiveData<Round> = MutableLiveData()

    init {
        val roundDao = GameDatabase.getInstance(application).roundDao()
        roundRepository = RoundRepository(roundDao)

        val playerDao = GameDatabase.getInstance(application).playerDao()
        playerRepository = PlayerRepository(playerDao)

        players = playerRepository.allPlayers

        loadRound()
    }

    fun loadRound() {
        if (roundNumber == Round.TOTALS_ROUND)
            loadTotals()
        else
            viewModelScope.launch(Dispatchers.IO) {
                round.postValue(roundRepository.getRoundNotLive(roundNumber))
            }
    }

    private fun loadTotals() {
        viewModelScope.launch(Dispatchers.IO) {
            val playersList = playerRepository.getPlayersNotLive()

            val totalsRound = Round(Round.TOTALS_ROUND)
            val rounds = roundRepository.getRoundsNotLive()

            for (player: Player in playersList) {
                var score = 0

                for (round: Round in rounds) {
                    round.scores[player.playerNumber]?.let {
                        score += it
                    }
                }

                totalsRound.scores[player.playerNumber] = score
            }

            round.postValue(totalsRound)
        }
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.insertPlayer(player)

            roundRepository.addPlayerForAllRounds(player)

            loadRound()
        }
    }

    override fun onDecrementTap(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            round.value?.let { round ->
                round.scores[id]?.let {
                    round.scores[id] = it - 1

                    roundRepository.updateRound(round)
                }
            }
        }
    }

    override fun onDecrementLongPress(id: Int): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            round.value?.let { round ->
                round.scores[id]?.let {
                    round.scores[id] = it - 5

                    roundRepository.updateRound(round)
                }
            }
        }

        return true
    }

    override fun onIncrementTap(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            round.value?.let { round ->
                round.scores[id]?.let {
                    round.scores[id] = it + 1

                    roundRepository.updateRound(round)
                }
            }
        }
    }

    override fun onIncrementLongPress(id: Int): Boolean {
        viewModelScope.launch(Dispatchers.IO) {
            round.value?.let { round ->
                round.scores[id]?.let {
                    round.scores[id] = it + 5

                    roundRepository.updateRound(round)
                }
            }
        }

        return true
    }

    override fun onScoreDisplayTap(id: Int) {

    }

    override fun onScoreDisplayLongPress(id: Int): Boolean {
        return true
    }
}

class RoundViewModelFactory(private val application: Application, private val roundNumber: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoundViewModel(application, roundNumber) as T
    }
}
