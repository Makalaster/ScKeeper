package com.makalaster.sckeeper.gameactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RoundViewModel() : ViewModel() {
    // TODO: Implement the ViewModel
}

class RoundViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoundViewModel() as T
    }
}
