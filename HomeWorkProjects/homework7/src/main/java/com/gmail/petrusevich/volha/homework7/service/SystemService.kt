package com.gmail.petrusevich.volha.homework7.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.gmail.petrusevich.volha.homework7.R
import com.gmail.petrusevich.volha.homework7.logger.Logger
import com.gmail.petrusevich.volha.homework7.logger.LoggerTextController

private const val CHANNEL_ID = "SYSTEM_CHANNEL"
private const val FOREGROUND_ID = 1
private const val TITLE_NOTIFICATION = "System message"
private const val TEXT_NOTIFICATION = "Logger is working"

class SystemService : Service() {

    private val logger = Logger()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotification(baseContext)
        val text = LoggerTextController.textLogger
        logger.writeLog(text)
        return super.onStartCommand(intent, flags, startId)

    }

    private fun createNotification(context: Context) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(TITLE_NOTIFICATION)
                .setContentText(TEXT_NOTIFICATION)
                .build()
        startForeground(FOREGROUND_ID, notification)
    }


}