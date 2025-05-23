package com.example.expensetracker.data

import android.content.Context

interface AppContainer {
    val appRepositories: AppRepositories
}

class AppContainerImpl(val context: Context) : AppContainer {
    override val appRepositories: AppRepositories by lazy {
        AppRepositoriesImpl(expenseDao = ExpenseTrackerDataBase.createDb(context).expenseDao())
    }


}