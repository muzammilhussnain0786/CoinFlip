package com.coinflip.takehome.data.remote.di

import com.coinflip.takehome.data.remote.CoinGeckoService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {

    protected open fun baseUrl() = "https://api.coingecko.com/api/v3/".toHttpUrl()


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofitClient(json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinGeckoService(retrofit: Retrofit) : CoinGeckoService {
        return retrofit.create(CoinGeckoService::class.java)
    }

    @Provides
    @Singleton
    fun provideKotlinSerializationJson(): Json = Json{
        ignoreUnknownKeys = true
        isLenient = true
    }

}