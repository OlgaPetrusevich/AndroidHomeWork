package com.gmail.petrusevich.volha.homework5.database.controller

import androidx.lifecycle.LiveData
import com.gmail.petrusevich.volha.homework5.database.ContactDao
import com.gmail.petrusevich.volha.homework5.database.Contacts

class ContactRepository(
        private val contactDao: ContactDao?
) : Repository {

    override fun getAllContacts(): LiveData<List<Contacts>>? {
        return contactDao?.getAllContacts()
    }

    override fun getContact(idContact: Int): Contacts? {
        return contactDao?.getContact(idContact)
    }

    override fun getSearchContacts(search: String?): List<Contacts>? {
        return contactDao?.getSearchList(search)
    }

    override fun insert(contact: Contacts?) {
        contactDao?.insertContact(contact)
    }

    override fun delete(contact: Contacts?) {
        contactDao?.deleteContact(contact)
    }

    override fun update(contact: Contacts?) {
        contactDao?.updateContact(contact)
    }


}