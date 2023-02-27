package com.coinflip.takehome.data.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.coinflip.takehome.data.local.database.AppDatabase
import com.coinflip.takehome.data.local.database.CoinEntityDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideCoinMarketEntityDao(appDatabase: AppDatabase): CoinEntityDao {
        return appDatabase.coinMarketEntityDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "coins"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
