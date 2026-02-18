package com.example.expensetracker

import android.app.Application
import com.example.expensetracker.data.AppContainer
import com.example.expensetracker.data.AppContainerImpl
import com.google.android.gms.ads.MobileAds

class ExpenseTrackerApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
        MobileAds.initialize(this)
    }


}