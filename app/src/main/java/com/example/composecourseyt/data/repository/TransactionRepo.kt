package com.example.composecourseyt.data.repository

import com.example.composecourseyt.data.local.TransactionDao
import com.example.composecourseyt.data.local.entities.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepo @Inject constructor(private val transactionDao: TransactionDao) {

    suspend fun insertData(transaction: Transaction) {
        transactionDao.upsertTransaction(transaction)
    }

    fun getTransactionData(): Flow<List<Transaction>> {
        return transactionDao.getTransactionOrderedByAmount()
    }

    //for previous records
    suspend fun getPreviousDayTransactions(startOfDay: String, endOfDay: String): Flow<List<Transaction>> {
        return transactionDao.getPreviousDayTransactions(startOfDay, endOfDay)
    }

    fun calculateSum(transactions: List<Transaction>): Int {
        var sum = 0
        for (transaction in transactions) {
            sum += transaction.amount.toInt()
        }
        return sum
    }
}
