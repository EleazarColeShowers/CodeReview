package com.example.composecourseyt.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composecourseyt.data.local.entities.Transaction

@Database(
    entities = [Transaction::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

}