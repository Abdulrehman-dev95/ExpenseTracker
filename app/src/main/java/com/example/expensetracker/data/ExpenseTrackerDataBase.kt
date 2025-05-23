package com.example.expensetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1)
abstract class ExpenseTrackerDataBase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
      private var instance: ExpenseTrackerDataBase? = null
        fun createDb(context: Context): ExpenseTrackerDataBase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    ExpenseTrackerDataBase::class.java,
                    "expenseTracker_database"
                ).build().also { instance = it }
            }
        }

    }


}