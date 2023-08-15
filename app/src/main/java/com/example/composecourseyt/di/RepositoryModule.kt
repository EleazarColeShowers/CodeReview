package com.example.composecourseyt.di

import com.example.composecourseyt.data.local.TransactionDao
import com.example.composecourseyt.data.repository.TransactionRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    fun provideTransactionRepo(transactionDao: TransactionDao) : TransactionRepo{
        return TransactionRepo(transactionDao)
    }
}