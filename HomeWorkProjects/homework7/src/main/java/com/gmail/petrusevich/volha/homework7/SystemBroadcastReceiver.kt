package com.gmail.petrusevich.volha.homework7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.*

class SystemBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val calendar = Calendar.getInstance()
        val date = calendar.time
        LoggerTextController.getLoggerText(date, intent)
        context?.startService(Intent(context, SystemService::class.java))
    }
}