package com.coinflip.takehome.ui.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.coinflip.takehome.domain.CoinMarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinMarketViewModel @Inject constructor(
    private val coinMarketRepository: CoinMarketRepository
) : ViewModel() {

    fun getCoins() = coinMarketRepository.coinMarketEntities.cachedIn(viewModelScope)
}
