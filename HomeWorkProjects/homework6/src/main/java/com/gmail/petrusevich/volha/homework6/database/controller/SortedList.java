package com.gmail.petrusevich.volha.homework6.database.controller;

import com.gmail.petrusevich.volha.homework6.database.Contacts;

import java.util.List;

public class SortedList {

    public int getSortedPosition(List<Contacts> list, Contacts contact) {
        int position = 0;
        for (Contacts contactList : list) {
            if (contact.getName().compareToIgnoreCase(contactList.getName()) < 0) {
                position = list.indexOf(contactList);
                break;
            } else {
                position = list.indexOf(contactList) + 1;
            }
        }
        return position;
    }
}
