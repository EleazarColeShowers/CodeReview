package com.example.composecourseyt.di

import android.app.Application
import androidx.room.Room
import com.example.composecourseyt.data.Constants.DATABASE_NAME
import com.example.composecourseyt.data.local.AppDatabase
import com.example.composecourseyt.data.local.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesTransactionDAO(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

}