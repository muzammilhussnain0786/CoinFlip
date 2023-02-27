package com.coinflip.takehome.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.coinflip.takehome.data.local.database.CoinEntity
import com.coinflip.takehome.data.local.database.CoinEntityDao
import com.coinflip.takehome.data.sources.MarketRemoteMediator
import javax.inject.Inject

interface CoinMarketRepository {
    fun getCoin(coinId: String): Flow<CoinEntity>

    val coinMarketEntities: Flow<PagingData<CoinEntity>>
}

class CoinMarketRepositoryImpl @Inject constructor(
    private val coinEntityDao: CoinEntityDao,
    mediator: MarketRemoteMediator
) : CoinMarketRepository {

    @OptIn(ExperimentalPagingApi::class)
    private val pager = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            prefetchDistance = 1
        ),
        remoteMediator = mediator,
        pagingSourceFactory = { coinEntityDao.getCoinMarketPageSource() }
    ).flow

    override fun getCoin(coinId: String): Flow<CoinEntity> = coinEntityDao.getCoin(coinId)

    override val coinMarketEntities: Flow<PagingData<CoinEntity>> = pager

}
