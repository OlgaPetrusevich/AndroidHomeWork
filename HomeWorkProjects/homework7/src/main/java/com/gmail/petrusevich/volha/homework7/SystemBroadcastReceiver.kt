package com.gmail.petrusevich.volha.homework7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

class SystemBroadcastReceiver : BroadcastReceiver() {

    private val logger = Logger()

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d("broadcast", intent?.action.toString())
        val calendar = Calendar.getInstance()
        val date = calendar.time
        val text = logger.createText(date, intent)
        intent?.putExtra("KEY", text)
    }
}