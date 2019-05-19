package com.makalaster.sckeeper.gameactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.makalaster.data.database.GameDatabase
import com.makalaster.data.database.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val playerRepository: PlayerRepository

    init {
        val playerDao = GameDatabase.getInstance(application).playerDao()
        playerRepository = PlayerRepository(playerDao)
    }

    fun clearPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.clearPlayers()
        }
    }
}