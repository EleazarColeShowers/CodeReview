package com.example.composecourseyt.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class Transaction(
    val amount: String,
    val timestamp: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0

)
