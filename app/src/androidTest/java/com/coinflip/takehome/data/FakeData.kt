package com.coinflip.takehome.data

import com.coinflip.takehome.data.local.database.CoinEntity

object FakeData {

    val bitcoin = CoinEntity(
        id = "bitcoin",
        name = "bitcoin",
        symbol = "bitcoin",
        price = 0.0,
        image = "",
        market_cap = 0.0,
        total_supply = 0.0,
        total_volume = 0.0
    )
    val ethereum = CoinEntity(
        id = "ethereum",
        name = "ethereum",
        symbol = "ethereum",
        price = 0.0,
        image = "",
        market_cap = 0.0,
        total_supply = 0.0,
        total_volume = 0.0
    )
    val listOfEntities = listOf(bitcoin, ethereum)

}