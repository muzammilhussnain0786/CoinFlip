package com.coinflip.takehome.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class CoinNetwork(
    val ath: Double? = null,
    val ath_change_percentage: Double? = null,
    val ath_date: String? = null,
    val atl: Double? = null,
    val atl_change_percentage: Double? = null,
    val atl_date: String? = null,
    val circulating_supply: Double? = null,
    val current_price: Double = 0.0,
    val fully_diluted_valuation: Double? = null,
    val high_24h: Double? = null,
    val id: String,
    val image: String,
    val last_updated: String? = null,
    val low_24h: Double? = null,
    val market_cap: Double = 0.0,
    val market_cap_change_24h: Double? = null,
    val market_cap_change_percentage_24h: Double? = null,
    val market_cap_rank: Double? = null,
    val max_supply: Double? = null,
    val name: String,
    val price_change_24h: Double? = null,
    val price_change_percentage_24h: Double? = null,
    val symbol: String,
    val total_supply: Double? = null,
    val total_volume: Double? = null
)