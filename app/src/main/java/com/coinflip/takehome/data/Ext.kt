package com.coinflip.takehome.data

import com.coinflip.takehome.data.local.database.CoinEntity
import com.coinflip.takehome.data.remote.models.CoinNetwork

fun List<CoinNetwork>.toCoinMarketEntityList(): List<CoinEntity> {
    return this.map { it.toCoinMarketEntity() }
}

fun CoinNetwork.toCoinMarketEntity() : CoinEntity {
    return CoinEntity(
        id = id,
        image = image,
        market_cap = market_cap,
        name = name,
        symbol = symbol,
        total_supply = total_supply,
        total_volume = total_volume,
        price = current_price
    )
}