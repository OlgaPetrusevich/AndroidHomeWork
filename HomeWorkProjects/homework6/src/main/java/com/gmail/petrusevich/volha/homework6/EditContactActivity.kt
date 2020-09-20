package com.gmail.petrusevich.volha.homework6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.petrusevich.volha.homework6.database.datacontact.ContactInfo
import kotlinx.android.synthetic.main.activity_edit_contact.*

const val KEY_ACTION = "action"

class EditContactActivity : AppCompatActivity(), View.OnClickListener {

    private val contactInfo = ContactInfo.getInstance()
    private var isRemove = false

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, EditContactActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)
        showContactInfo()
        viewBackButtonEditContactActivity.setOnClickListener(this)
        viewCheckButtonEditContactActivity.setOnClickListener(this)
        viewRemoveButton.setOnClickListener(this)
    }

    private fun showContactInfo() {
        viewNameEdit.setText(contactInfo.nameContact)
        viewDataEdit.setText(contactInfo.dataContact)
    }


    override fun onClick(view: View?) {
        when (view) {
            viewBackButtonEditContactActivity -> finish()
            viewCheckButtonEditContactActivity -> {
                actionContact()
                finish()
            }
            viewRemoveButton -> {
                isRemove = true
                actionContact()
                finish()
            }
        }
    }


    private fun actionContact() {
        if (!isRemove) {
            contactInfo.nameContact = viewNameEdit.text.toString()
            contactInfo.dataContact = viewDataEdit.text.toString()
        }
        val intent = Intent()
        intent.putExtra(KEY_ACTION, isRemove)
        setResult(Activity.RESULT_OK, intent)
    }

}