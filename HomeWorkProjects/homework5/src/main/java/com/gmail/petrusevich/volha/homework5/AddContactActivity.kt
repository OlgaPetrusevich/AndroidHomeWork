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

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, AddContactActivity::class.java)
        }
    }

    private var dataType: ContactDataType = ContactDataType.PHONE
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

    private fun addContact(contactRepository: ContactRepository) {
        val name = viewNameAdd.text.toString()
        val data = viewDataAdd.text.toString()
        val contact = Contacts(name, data, dataType)
        contactRepository.insert(contact)
    }

}