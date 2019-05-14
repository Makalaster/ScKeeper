package com.makalaster.sckeeper.gameactivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RoundViewModel() : ViewModel() {
    // TODO: Implement the ViewModel
}

class RoundViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RoundViewModel() as T
    }
}
