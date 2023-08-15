package com.example.composecourseyt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecourseyt.data.local.entities.Transaction
import com.example.composecourseyt.data.repository.TransactionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepo

): ViewModel() {

    private val _sumStateFlow = MutableStateFlow(0)
    val sumStateFlow: StateFlow<Int>
        get() = _sumStateFlow

    init {
        getTransaction()
        loadSumOfRecordsFromPreviousDay()
    }

    fun insertTransaction(transaction: Transaction){
        viewModelScope.launch(IO) {
            repository.insertData(transaction)
        }
    }

    private fun getTransaction(){
        viewModelScope.launch {
            repository.getTransactionData().collect{
                Log.d("database","$it")
            }
        }
    }


    fun loadSumOfRecordsFromPreviousDay() {
        val today = Calendar.getInstance()
        today.set(Calendar.HOUR_OF_DAY, 0)
        today.set(Calendar.MINUTE, 0)
        today.set(Calendar.SECOND, 0)
        today.set(Calendar.MILLISECOND, 0)

        val startOfDay = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(today.time)
        val endOfDay =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(today.timeInMillis + (24 * 60 * 60 * 1000))

        viewModelScope.launch {
            val transactions = repository.getPreviousDayTransactions(startOfDay, endOfDay)
            val sum = calculateSum(transactions)
            _sumStateFlow.value = sum
        }
    }



    private suspend fun calculateSum(transactions: Flow<List<Transaction>>): Int {
        var sum = 0
        transactions.collect { transactionList ->
            for (transaction in transactionList) {
                sum += transaction.amount.toInt()
            }
        }
        return sum
    }

}