package com.coinflip.takehome.ui.coindetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.coinflip.takehome.domain.CoinMarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor (
    savedStateHandle: SavedStateHandle,
    repository: CoinMarketRepository
) : ViewModel() {

    private val coinId = savedStateHandle.get<String>(COIN_ID_SAVED_STATE_KEY)!!

    val coin = repository.getCoin(coinId)

    companion object {
        private const val COIN_ID_SAVED_STATE_KEY = "coinId"
    }
}