package com.example.expensetracker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.AppRepositories
import com.example.expensetracker.data.Expense
import com.example.expensetracker.utils.Utils
import kotlinx.coroutines.launch

class AddExpenseScreenViewModel(private val appRepositories: AppRepositories) : ViewModel() {

    var addExpenseScreenState by mutableStateOf(
        AddExpenseUiState()
    )
        private set

    fun updateExpenseDetails(expenseDetails: ExpenseDetails) {
        addExpenseScreenState = addExpenseScreenState.copy(
            expenseDetails = expenseDetails
        )
    }

    fun onDateChange(date: Long) {
        val newDate = Utils.dateFormater(date = date)
        val expenseDetails = addExpenseScreenState.expenseDetails.copy(
            date = newDate
        )
        updateExpenseDetails(expenseDetails)
    }

    fun isEnabled(): Boolean {
        return !with(addExpenseScreenState) {
            expenseDetails.name.isBlank() ||
                    expenseDetails.amount.isBlank() ||
                    expenseDetails.date.isBlank() ||
                    expenseDetails.category.isBlank() ||
                    expenseDetails.type.isBlank()
        }

    }

    fun onAddButtonClick() {
        viewModelScope.launch {
            appRepositories.addExpense(addExpenseScreenState.expenseDetails.toExpense())
        }
    }

    data class AddExpenseUiState(
        val expenseDetails: ExpenseDetails = ExpenseDetails(),
        val isExpenseAdded: Boolean = false
    )

}

data class ExpenseDetails(
    val id: Int = 0,
    val name: String = "",
    val amount: String = "",
    val date: String = "",
    val category: String = "",
    val type: String = ""

)

fun ExpenseDetails.toExpense(): Expense {

    return Expense(
        id = id,
        name = name,
        amount = amount.toDouble(),
        date = date.toLong(),
        type = type,
        category = category,
    )


}

