package com.gmail.petrusevich.volha.homework6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.petrusevich.volha.homework6.database.ContactDataType
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, AddContactActivity::class.java)
        }
    }

    private var dataType: ContactDataType = ContactDataType.PHONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        viewBackButton.setOnClickListener(this)
        viewCheckButton.setOnClickListener(this)
        clickRadioGroup()
    }

    override fun onClick(view: View?) {
        when (view) {
            viewBackButton -> finish()
            viewCheckButton -> {
                addContact()
                finish()
            }
        }
    }

    private fun clickRadioGroup() {
        viewGroupButton.setOnCheckedChangeListener { viewGroupButton, i ->

            when (i) {
                R.id.viewPhoneButton -> {
                    viewDataAdd.setRawInputType(InputType.TYPE_CLASS_PHONE)
                    viewDataAdd.setHint(R.string.phone)
                    dataType = ContactDataType.PHONE
                }
                R.id.viewEmailButton -> {
                    viewDataAdd.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                    viewDataAdd.setHint(R.string.email)
                    dataType = ContactDataType.MAIL
                }
            }
        }
    }

    private fun addContact() {
        val name = viewNameAdd.text.toString()
        val data = viewDataAdd.text.toString()
        val intent = Intent()
        intent.putExtra("name", name)
        intent.putExtra("data", data)
        intent.putExtra("type", dataType)
        setResult(Activity.RESULT_OK, intent)
    }

}