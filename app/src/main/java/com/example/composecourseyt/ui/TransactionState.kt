package com.example.composecourseyt.ui

import com.example.composecourseyt.data.SortType
import com.example.composecourseyt.data.local.entities.Transaction

data class TransactionState(
    val transaction:List<Transaction> = emptyList(),
    val amount:String = "",
    val timestamp: String= "",
    val isAddingAmount:Boolean = false,
    val sortType: SortType = SortType.AMOUNT
)

