package com.gmail.petrusevich.volha.homework7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val broadcastReceiverController by lazy { BroadcastReceiverController() }
    private val systemBroadcastReceiver by lazy { SystemBroadcastReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(systemBroadcastReceiver, broadcastReceiverController.createBroadcastReceiver())
        FileDirCreate.getFileDir(this, StorageType.INTERNAL)
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemBroadcastReceiver)
        stopService(Intent(this, SystemService::class.java))
    }

}
