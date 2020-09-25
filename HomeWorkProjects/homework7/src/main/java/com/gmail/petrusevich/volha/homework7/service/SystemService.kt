package com.gmail.petrusevich.volha.homework7.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.gmail.petrusevich.volha.homework7.logger.Logger
import com.gmail.petrusevich.volha.homework7.logger.LoggerTextController

class SystemService : Service() {

    private val logger = Logger()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val text = LoggerTextController.textLogger
        logger.writeLog(text)
        return super.onStartCommand(intent, flags, startId)

    }


}