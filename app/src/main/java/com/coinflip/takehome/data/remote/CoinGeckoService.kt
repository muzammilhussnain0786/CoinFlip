package com.coinflip.takehome.data.remote

import com.coinflip.takehome.data.remote.models.CoinNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoService {

    @GET("coins/markets?sparkline=false")
    suspend fun getCoins(
        @Query("vs_currency") vs_currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") per_page: Int = 10,
        @Query("page") page: Int = 1
    ): List<CoinNetwork>
}