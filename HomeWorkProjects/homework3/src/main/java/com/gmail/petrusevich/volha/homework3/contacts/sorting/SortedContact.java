package com.gmail.petrusevich.volha.homework3.contacts.sorting;

import com.gmail.petrusevich.volha.homework3.contacts.listcontacts.Contacts;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedContact {

    private List<Contacts> contacts;

    public SortedContact(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public void sortContact(List<Contacts> contacts) {
        Collections.sort(contacts, new Comparator<Contacts>() {

            @Override
            public int compare(Contacts contact1, Contacts contact2) {
                String name1 = contact1.getName();
                String name2 = contact2.getName();
                return name1.compareTo(name2);
            }
        });
    }


}
