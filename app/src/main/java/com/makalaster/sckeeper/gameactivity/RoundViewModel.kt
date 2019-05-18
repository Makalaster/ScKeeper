package com.makalaster.sckeeper.gameactivity

import android.app.Application
import androidx.lifecycle.*
import com.makalaster.data.database.GameDatabase
import com.makalaster.data.database.PlayerRepository
import com.makalaster.data.models.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoundViewModel(application: Application) : AndroidViewModel(application) {
    private val playerRepository: PlayerRepository
    private val players: LiveData<List<Player>>

    init {
        val playerDao = GameDatabase.getInstance(application).playerDao()
        playerRepository = PlayerRepository(playerDao)

        players = playerRepository.allPlayers
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.insertPlayer(player)
        }
    }

    fun getPlayers(): LiveData<List<Player>> {
        return players
    }
}

class RoundViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoundViewModel(application) as T
    }
}
