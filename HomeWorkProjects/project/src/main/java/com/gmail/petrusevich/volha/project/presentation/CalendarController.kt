package com.gmail.petrusevich.volha.project.presentation

import java.time.LocalDate

class CalendarController {

    fun getCalendarDate(year: Int, month: Int, dayOfMonth: Int): String {
        val dateCalendar: LocalDate = LocalDate.of(year, (month + 1), dayOfMonth)
        return dateCalendar.toString()
    }
}