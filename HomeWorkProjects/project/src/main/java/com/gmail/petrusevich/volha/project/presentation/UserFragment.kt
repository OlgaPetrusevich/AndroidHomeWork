package com.gmail.petrusevich.volha.project.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.petrusevich.volha.project.R

class UserFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_profile_tab, container, false)

    companion object {
        const val TAG = "UserFragment"
        fun getInstance() = UserFragment()
    }
}