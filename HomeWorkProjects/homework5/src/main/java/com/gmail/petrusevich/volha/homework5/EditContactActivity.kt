package com.gmail.petrusevich.volha.homework5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gmail.petrusevich.volha.homework5.database.ContactDao
import com.gmail.petrusevich.volha.homework5.database.ContactDatabase
import com.gmail.petrusevich.volha.homework5.database.Contacts
import com.gmail.petrusevich.volha.homework5.database.controller.ContactRepository
import kotlinx.android.synthetic.main.activity_edit_contact.*

const val KEY_ACTION = "action"

class EditContactActivity : AppCompatActivity(), View.OnClickListener {

    private val contactRepository: ContactRepository
    private var contact: Contacts? = null

    init {
        val contactDao: ContactDao? = ContactDatabase.getInstance(this)?.getContactDao()
        contactRepository = ContactRepository(contactDao)
    }

    companion object {
        fun newIntent(context: Context?, id: Int?): Intent {
            val intent = Intent(context, EditContactActivity::class.java)
            intent.putExtra(KEY_ACTION, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)
        showContactInfo(contactRepository)
        viewBackButtonEditContactActivity.setOnClickListener(this)
        viewCheckButtonEditContactActivity.setOnClickListener(this)
        viewRemoveButton.setOnClickListener(this)
    }

    private fun showContactInfo(contactRepository: ContactRepository) {
        val id = intent.getIntExtra(KEY_ACTION, 0)
        contact = contactRepository.getContact(id)
        viewNameEdit.setText(contact?.name)
        viewDataEdit.setText(contact?.contactData)
    }


    override fun onClick(view: View?) {
        when (view) {
            viewBackButtonEditContactActivity -> finish()
            viewCheckButtonEditContactActivity -> {
                editContact(contactRepository)
                finish()
            }
            viewRemoveButton -> {
                contactRepository.delete(contact!!)
                finish()
            }
        }
    }

    private fun editContact(contactRepository: ContactRepository) {
        contact?.name = viewNameEdit.text.toString()
        contact?.contactData = viewDataEdit.text.toString()
        contactRepository.update(contact!!)
    }
}