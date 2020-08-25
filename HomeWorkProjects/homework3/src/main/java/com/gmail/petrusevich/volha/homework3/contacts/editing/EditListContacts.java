package com.gmail.petrusevich.volha.homework3.contacts.editing;

import android.content.Intent;

import com.gmail.petrusevich.volha.homework3.R;
import com.gmail.petrusevich.volha.homework3.contacts.activity.AddContactActivity;
import com.gmail.petrusevich.volha.homework3.contacts.activity.EditContactActivity;
import com.gmail.petrusevich.volha.homework3.contacts.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework3.contacts.listcontacts.Contacts;
import com.gmail.petrusevich.volha.homework3.contacts.sorting.SortedContact;

import java.util.List;

public class EditListContacts {

    private static final String KEY_MAIL = "@";

    public void addContact(Intent data, List<Contacts> listContacts, ContactListAdapter contactListAdapter) {
        String name = data.getStringExtra(AddContactActivity.KEY_NAME);
        String contactData = data.getStringExtra(AddContactActivity.KEY_DATA);
        int image;
        if (contactData == null) {
            return;
        } else if (contactData.contains(KEY_MAIL)) {
            image = R.drawable.contact_mail;
        } else {
            image = R.drawable.contact_phone;
        }
        listContacts.add(new Contacts(name, contactData, image));
        new SortedContact(listContacts).sortContact(listContacts);
        contactListAdapter.setListContactsCopy(listContacts);
        contactListAdapter.notifyDataSetChanged();
    }

    public void editContact(Intent data, List<Contacts> listContacts, ContactListAdapter contactListAdapter, int position) {
        Contacts contact = (Contacts) data.getParcelableExtra(EditContactActivity.KEY_EDIT);
        if (contact != null) {
            String name = contact.getName();
            String contactData = contact.getContactData();
            listContacts.get(position).setName(name);
            listContacts.get(position).setContactData(contactData);
        }
        new SortedContact(listContacts).sortContact(listContacts);
        contactListAdapter.notifyDataSetChanged();
    }

    public void removeContact(Intent data, List<Contacts> listContacts, ContactListAdapter contactListAdapter, int position) {
        boolean isContact = data.getBooleanExtra(EditContactActivity.KEY_REMOVE, true);
        if (!isContact) {
            listContacts.remove(position);
        }
        contactListAdapter.setListContactsCopy(listContacts);
        contactListAdapter.notifyDataSetChanged();
    }
}