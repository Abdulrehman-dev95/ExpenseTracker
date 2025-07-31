package com.example.expensetracker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.AppRepositories
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatsScreenViewModel(
    val appRepositories: AppRepositories
) : ViewModel() {


    private val _chartEntries = MutableStateFlow(
        listOf<Entry>()
    )
    val chartEntries = _chartEntries.asStateFlow()


    fun updateChartData(type: String = "Expense") {
        viewModelScope.launch {
            appRepositories.getExpenseByType(type).collect { expenses ->
                val data = expenses.map {
                    Entry(it.date.toFloat(), it.totalAmount.toFloat())
                }
                _chartEntries.value = data
            }
        }
    }

    init {
        updateChartData()
    }


}