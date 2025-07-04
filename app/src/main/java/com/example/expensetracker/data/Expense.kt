package com.example.expensetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val amount: Double,
    val date: Long,
    val type: String,
    val category: String,
)

data class ExpenseSummary(
    val type: String,
    val date: Long,
    @ColumnInfo(name = "total_amount") val totalAmount: Double

)