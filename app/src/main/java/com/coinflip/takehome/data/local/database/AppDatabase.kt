package com.coinflip.takehome.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoinEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coinMarketEntityDao(): CoinEntityDao
}
