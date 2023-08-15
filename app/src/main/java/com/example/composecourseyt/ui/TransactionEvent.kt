package com.example.composecourseyt.ui

import com.example.composecourseyt.data.SortType
import com.example.composecourseyt.data.local.entities.Transaction

sealed interface TransactionEvent{
    object SaveTransaction: TransactionEvent
    data class SetAmount(val amount: String): TransactionEvent
    data class SetTimestamp(val timestamp: String): TransactionEvent
    data class SortTransactions(val sortType: SortType): TransactionEvent
    data class DeleteTransaction(val transaction: Transaction): TransactionEvent
}