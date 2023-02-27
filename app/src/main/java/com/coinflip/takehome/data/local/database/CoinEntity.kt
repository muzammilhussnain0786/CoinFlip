package com.coinflip.takehome.data.local.database

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class CoinEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val image: String,
    val market_cap: Double,
    val name: String,
    val symbol: String,
    val total_supply: Double?,
    val total_volume: Double?,
    val price: Double
)

@Dao
interface CoinEntityDao {

    @Query("SELECT * FROM CoinEntity ORDER BY market_cap DESC")
    fun getCoinsMarket(): Flow<List<CoinEntity>>

    @Query("SELECT * FROM CoinEntity ORDER BY market_cap DESC")
    fun getCoinMarketPageSource(): PagingSource<Int, CoinEntity>

    @Insert
    suspend fun insert(item: CoinEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CoinEntity>)

    @Query("SELECT * FROM CoinEntity WHERE id = :coinId")
    fun getCoin(coinId: String): Flow<CoinEntity>
}
