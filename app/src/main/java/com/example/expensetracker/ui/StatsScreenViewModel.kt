package com.example.expensetracker.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.AppRepositories
import com.example.expensetracker.utils.Utils
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StatsScreenViewModel(
    appRepositories: AppRepositories
) : ViewModel() {



    val chartEntries = mutableStateOf(
        listOf<Entry>()
    )





    init {
        viewModelScope.launch {
            appRepositories.getExpenseByType().collect { expenses ->
                val data = expenses.map {
                    Entry(it.date.toFloat(), it.totalAmount.toFloat())
                }
                chartEntries.value = data
            }
        }
    }


}