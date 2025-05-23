package com.example.expensetracker.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun dateFormater(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
        val newDate = simpleDateFormat.format(Date(date))
        return newDate
    }

    fun currencyFormatter(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        return format.format(amount)


    }


}