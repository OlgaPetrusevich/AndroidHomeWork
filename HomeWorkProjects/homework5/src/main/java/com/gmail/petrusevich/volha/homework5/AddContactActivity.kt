package com.gmail.petrusevich.volha.homework5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.petrusevich.volha.homework5.database.ContactDao
import com.gmail.petrusevich.volha.homework5.database.ContactDataType
import com.gmail.petrusevich.volha.homework5.database.ContactDatabase
import com.gmail.petrusevich.volha.homework5.database.Contacts
import com.gmail.petrusevich.volha.homework5.database.controller.ContactRepository
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity(), View.OnClickListener {

    private val contactRepository: ContactRepository

    init {
        val contactDao: ContactDao? = ContactDatabase.getInstance(this)?.getContactDao()
        contactRepository = ContactRepository(contactDao)
    }

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
                addContact(contactRepository)
                finish()
            }
        }
    }

    private fun clickRadioGroup() {
        viewGroupButton.setOnCheckedChangeListener { viewGroupButton, viewButton ->

            when (viewButton) {
                R.id.viewPhoneButton -> {
                    viewDataAdd.setRawInputType(InputType.TYPE_CLASS_PHONE)
                    viewDataAdd.setHint(R.string.phone)
                }
                R.id.viewEmailButton -> {
                    viewDataAdd.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                    viewDataAdd.setHint(R.string.email)
                }
            }
        }
    }

    private fun addContact(contactRepository: ContactRepository) {
        val name = viewNameAdd.text.toString()
        val data = viewDataAdd.text.toString()
        val contact = contactTypeDefinition(name, data)
        contactRepository.insert(contact)
    }

    private fun contactTypeDefinition(name: String, data: String): Contacts {
        val dataType = if (viewPhoneButton.isChecked) {
            ContactDataType.PHONE
        } else {
            ContactDataType.MAIL
        }
        return Contacts(name, data, dataType)
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, AddContactActivity::class.java)
        }
    }

}