package com.gmail.petrusevich.volha.homework6;

import android.content.Intent;

import com.gmail.petrusevich.volha.homework6.database.ContactDataType;
import com.gmail.petrusevich.volha.homework6.database.Contacts;
import com.gmail.petrusevich.volha.homework6.database.controller.Repository;

public class DatabaseController {

    private ContactInfo contactInfo = ContactInfo.getInstance();

    public void addContact(Repository repository, Intent intent) {
        String contactName = intent.getStringExtra("name");
        String contactData = intent.getStringExtra("data");
        ContactDataType contactType = (ContactDataType) intent.getSerializableExtra("type");
        if (contactType != null) {
            Contacts contact = new Contacts(contactName, contactData, contactType);
            repository.insert(contact);
        }
    }

    public void showContactInfo(Repository repository, int position) {
        repository.getContact(position);
    }


    public void actionContact(Repository repository, boolean isRemove) {
        if (isRemove) {
            repository.delete(contactInfo.getIdContact());
        } else {
            repository.update(contactInfo.getIdContact());
        }
    }

}
