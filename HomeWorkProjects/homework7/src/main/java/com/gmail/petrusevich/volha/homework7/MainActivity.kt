package com.gmail.petrusevich.volha.homework7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val broadcastReceiverController by lazy { BroadcastReceiverController() }
    private val systemBroadcastReceiver by lazy { SystemBroadcastReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(systemBroadcastReceiver, broadcastReceiverController.createBroadcastReceiver())
        FileDirCreate.getFileDir(this, StorageType.INTERNAL)
//        startService(Intent(this, SystemService::class.java))
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemBroadcastReceiver)
    }
}
