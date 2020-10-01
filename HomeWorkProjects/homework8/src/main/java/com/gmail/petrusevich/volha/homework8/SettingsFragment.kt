package com.gmail.petrusevich.volha.homework8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(), View.OnClickListener {

    private val settings: Settings by lazy { Settings.getInstance() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settings.getSharedPreferences(context!!)
        settings.setSwitch(viewSwitch)
        viewSwitch.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when (view) {
            viewSwitch -> {
                if (viewSwitch.isChecked) {
                    viewSwitch.text = resources.getText(R.string.celsius_name)
                } else {
                    viewSwitch.text = resources.getText(R.string.fahrenheit_name)
                }
                settings.saveSettings(viewSwitch)
            }
        }
    }

    companion object {
        const val TAG = "SettingsFragment"
        fun gteInstance() = SettingsFragment()
    }
}