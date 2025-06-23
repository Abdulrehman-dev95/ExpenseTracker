package com.example.expensetracker.data

import kotlinx.coroutines.flow.Flow

interface AppRepositories {
    fun getList(): Flow<List<Expense>>
    suspend fun addExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun deleteExpense(id: Int)
    fun getExpenseByType(type: String = "Expense"): Flow<List<Expense>>
}

class AppRepositoriesImpl(private val expenseDao: ExpenseDao) : AppRepositories {

    override fun getList(): Flow<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    override suspend fun addExpense(expense: Expense) {
        expenseDao.addExpense(expense)
    }

    override suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    override suspend fun deleteExpense(id: Int) {
        expenseDao.deleteExpense(id)
    }

    override fun getExpenseByType(type: String): Flow<List<Expense>> {
    return expenseDao.getExpenseByType(type)
    }
}