package com.gmail.petrusevich.volha.homework9

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewShowContactButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            viewShowContactButton -> loadFragment()
        }
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, ContactFragment.gteInstance(), ContactFragment.TAG)
                .addToBackStack(null)
                .commit()
    }


}
