package com.coinflip.takehome.data.sources

import androidx.paging.*
import com.coinflip.takehome.data.local.database.CoinEntity
import com.coinflip.takehome.data.local.database.CoinEntityDao
import com.coinflip.takehome.data.remote.CoinGeckoService
import com.coinflip.takehome.data.toCoinMarketEntityList
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MarketRemoteMediator @Inject constructor(
    private val coinGeckoService: CoinGeckoService,
    private val coinEntityDao: CoinEntityDao
    ) : RemoteMediator<Int, CoinEntity>() {


    var page = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinEntity>
    ): MediatorResult {
        return try {
            val dataResponse = coinGeckoService.getCoins(page = ++page)
            coinEntityDao.insertAll(dataResponse.toCoinMarketEntityList())
            MediatorResult.Success(
                endOfPaginationReached = dataResponse.isEmpty()
            )
        } catch (exception: Exception){
            MediatorResult.Error(exception)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }

    }

}


