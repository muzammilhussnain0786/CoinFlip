package com.coinflip.takehome.network

import com.coinflip.takehome.data.remote.models.CoinNetwork

object FakeData {

    val bitcoin = CoinNetwork(
        id = "bitcoin",
        name = "bitcoin",
        symbol = "bitcoin",
        current_price = 0.0,
        image = "",
        market_cap = 0.0,
        total_supply = 0.0,
        total_volume = 0.0,
    )
    val ethereum = CoinNetwork(
        id = "ethereum",
        name = "ethereum",
        symbol = "ethereum",
        current_price = 0.0,
        image = "",
        market_cap = 0.0,
        total_supply = 0.0,
        total_volume = 0.0
    )
    val listOfEntities = listOf(bitcoin, ethereum)

}