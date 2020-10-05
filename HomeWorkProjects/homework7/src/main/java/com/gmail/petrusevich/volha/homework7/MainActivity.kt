package com.gmail.petrusevich.volha.homework7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.petrusevich.volha.homework7.broadcastreceiver.BroadcastReceiverController
import com.gmail.petrusevich.volha.homework7.broadcastreceiver.SystemBroadcastReceiver
import com.gmail.petrusevich.volha.homework7.logger.FileDirCreate
import com.gmail.petrusevich.volha.homework7.service.SystemService
import com.gmail.petrusevich.volha.homework7.settings.Settings
import com.gmail.petrusevich.volha.homework7.settings.StorageType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val broadcastReceiverController by lazy { BroadcastReceiverController() }
    private val systemBroadcastReceiver by lazy { SystemBroadcastReceiver() }
    private val settings by lazy { Settings.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settings.loadSettings(viewGroupButton)
        registerReceiver(systemBroadcastReceiver, broadcastReceiverController.createBroadcastReceiver())
        getStorageType()
        viewGroupButton.setOnCheckedChangeListener { viewGroupButton, viewButton ->
            getStorageType()
            settings.saveSettings(viewGroupButton)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(systemBroadcastReceiver)
        stopService(Intent(this, SystemService::class.java))
    }

    private fun getStorageType() {
        val storageType = if (viewButtonInternal.isChecked) {
            StorageType.INTERNAL
        } else {
            StorageType.EXTERNAL
        }
        FileDirCreate.getFileDir(this, storageType)
    }

}
