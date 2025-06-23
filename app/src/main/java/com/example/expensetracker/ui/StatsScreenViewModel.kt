package com.example.expensetracker.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.AppRepositories
import com.example.expensetracker.utils.Utils
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class StatsScreenViewModel(
    appRepositories: AppRepositories
) : ViewModel() {

   private val entries = appRepositories.getExpenseByType().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val chartEntries = mutableStateOf(
        listOf<Entry>()
    )


    private fun entriesForChart(
    ) {

        for (
        entry in entries.value
        ) {
            val formatDate = Utils.dateFormater(entry.date)
            chartEntries.value.plus(
                Entry(
                    formatDate.toFloat(),
                    entry.amount.toFloat()
                )
            )
        }


    }


}