package com.tmr.yheenca.todo.utilities

import java.text.SimpleDateFormat
import java.util.*

object CustomDate {

    private const val SIZE = 7

    val dates: List<Date> by lazy {
        setDates()
    }

    private fun setDates(): List<Date> {
        val dates = mutableListOf<Date>()
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, -cal.get(Calendar.DAY_OF_WEEK) + 2)
        for(i in 0 until SIZE){
            dates.add(i, Date(cal.timeInMillis))
            cal.add(Calendar.DAY_OF_MONTH, 1)
        }
        return dates
    }

    fun convertDate(pattern:  String): List<String> {
        val format = SimpleDateFormat(pattern, Locale.getDefault())
        return dates.map{
            format.format(it)
        }
    }

}

