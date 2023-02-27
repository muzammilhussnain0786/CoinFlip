package com.coinflip.takehome.domain.di

import androidx.paging.PagingData
import com.coinflip.takehome.data.local.database.CoinEntity
import com.coinflip.takehome.domain.CoinMarketRepository
import com.coinflip.takehome.domain.CoinMarketRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Singleton
    @Binds
    fun bindsCoinMarketRepository(
        coinMarketRepository: CoinMarketRepositoryImpl
    ): CoinMarketRepository
}
