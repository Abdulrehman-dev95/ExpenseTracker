package com.example.expensetracker.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.expensetracker.ExpenseTrackerApplication

object ViewModelInitializer {
    val factory = viewModelFactory {
        initializer {
            val application = expenseTrackerApplication().container
            HomeScreenViewModel(
                appRepositories = application.appRepositories
            )
        }

        initializer {

            AddExpenseScreenViewModel(
                appRepositories = expenseTrackerApplication().container.appRepositories
            )

        }
        initializer {
            StatsScreenViewModel(
                appRepositories = expenseTrackerApplication().container.appRepositories
            )

        }


    }


}

fun CreationExtras.expenseTrackerApplication(): ExpenseTrackerApplication {
    return (this[APPLICATION_KEY] as ExpenseTrackerApplication)
}