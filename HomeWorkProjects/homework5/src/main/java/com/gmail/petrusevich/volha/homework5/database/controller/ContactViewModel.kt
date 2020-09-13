package com.gmail.petrusevich.volha.homework5.database.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gmail.petrusevich.volha.homework5.database.ContactDao
import com.gmail.petrusevich.volha.homework5.database.ContactDatabase
import com.gmail.petrusevich.volha.homework5.database.Contacts
import com.gmail.petrusevich.volha.homework5.database.controller.ContactRepository

class ContactViewModel(app: Application) : AndroidViewModel(app) {

    private val contactRepository: ContactRepository
    val listContacts: LiveData<List<Contacts>>?


    init {
        val contactDao: ContactDao? = ContactDatabase.getInstance(app)?.getContactDao()
        contactRepository = ContactRepository(contactDao)
        listContacts = contactRepository.getAllContacts()
    }

    fun getSearchContact(search: String?): List<Contacts>? {
        return contactRepository.getSearchContacts(search)
    }


}