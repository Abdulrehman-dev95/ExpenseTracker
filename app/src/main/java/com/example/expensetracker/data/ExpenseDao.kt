package com.example.expensetracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): Flow<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("DELETE FROM expense WHERE id = :id")
    suspend fun deleteExpense(id: Int)

    @Query("SELECT type, date, SUM(amount) as total_amount FROM expense WHERE type = :type GROUP BY type, date ORDER by date")
    fun getExpenseByType(type: String): Flow<List<ExpenseSummary>>


}