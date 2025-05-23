package com.example.expensetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.AppRepositories
import com.example.expensetracker.data.Expense
import com.example.expensetracker.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val appRepositories: AppRepositories) : ViewModel() {


    private val _uiSate = MutableStateFlow(HomeScreenUi())
    val uiState: StateFlow<HomeScreenUi> = _uiSate.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val list = appRepositories.getList().collect { expenseList ->
                val expense = expenseList.filter {
                    it.type == "expense"
                }.sumOf {
                    it.amount
                }

                val income = expenseList.filter {
                    it.type == "income"
                }.sumOf {
                    it.amount

                }
                val balance = income - expense

                _uiSate.value = HomeScreenUi(
                    balance = Utils.currencyFormatter(balance),
                    expense = Utils.currencyFormatter(expense),
                    income = Utils.currencyFormatter(income),
                    expenseList = expenseList.map {
                        it.toExpenseDetails()
                    }
                )


            }


        }

    }


}

data class HomeScreenUi(
    val expenseList: List<ExpenseDetails> = emptyList(),
    val balance: String = "0.0",
    val expense: String = "0.0",
    val income: String = "0.o"
)


fun Expense.toExpenseDetails(): ExpenseDetails {
    return ExpenseDetails(
        id = id,
        name = name,
        amount = amount.toString(),
        date = date.toString(),
        type = type,
    )


}