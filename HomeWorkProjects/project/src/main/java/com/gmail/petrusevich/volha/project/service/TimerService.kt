package com.gmail.petrusevich.volha.project.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.gmail.petrusevich.volha.project.R

private const val CHANNEL_ID = "SYSTEM_CHANNEL"
private const val FOREGROUND_ID = 1
private const val TITLE_NOTIFICATION = "System message"
private const val TEXT_NOTIFICATION = "Timer is working"
//
//class TimerService() : Service() {
//
//
//    private val timerController: TimerController by lazy { TimerController() }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        return null
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
////        createNotification(baseContext)
//        timerController.getTimerTime()
//        return super.onStartCommand(intent, flags, startId)
//
//    }
//
//    private fun createNotification(context: Context) {
//        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_timer)
//                .setContentTitle(TITLE_NOTIFICATION)
//                .setContentText(TEXT_NOTIFICATION)
//                .build()
//        startForeground(FOREGROUND_ID, notification)
//    }
//
//
//}

