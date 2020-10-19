package com.gmail.petrusevich.volha.homework8.city

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.gmail.petrusevich.volha.homework8.R

class CityAddDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val intent = Intent()
        val view = activity?.layoutInflater?.inflate(R.layout.dialog_add_city, null)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(view)
                    .setTitle(R.string.enter_city_text)
                    .setCancelable(true)
                    .setPositiveButton(R.string.done_text) { dialog, i ->
                        intent.putExtra("KEY", getCityName(view?.findViewById(R.id.viewCityEditText)))
                        targetFragment?.onActivityResult(1, Activity.RESULT_OK, intent)
                    }
                    .setNegativeButton(R.string.cancel_text) { dialog, i ->
                        dialog.cancel()
                    }
            builder.create()
        } ?: throw IllegalStateException("ERROR")
    }

    private fun getCityName(view: EditText?): String {
        return view?.text.toString()
    }
}

