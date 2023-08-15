package com.example.composecourseyt.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.composecourseyt.data.local.entities.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Upsert
    suspend fun upsertTransaction(transaction: Transaction)


    @Delete
    suspend fun deleteTransaction(transaction: Transaction)


    @Query("SELECT * FROM 'transaction' ORDER BY timestamp ASC")
    fun getTransactionOrderedByTimestamp(): Flow<List<Transaction>>


    @Query("SELECT * FROM 'transaction' ORDER BY amount ASC")
    fun getTransactionOrderedByAmount(): Flow<List<Transaction>>


    //for previous records
    @Query("SELECT * FROM 'transaction' WHERE timestamp BETWEEN :startOfDay AND :endOfDay")
    fun getPreviousDayTransactions(startOfDay: String, endOfDay: String): Flow<List<Transaction>>


}