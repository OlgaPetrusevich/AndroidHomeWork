package com.gmail.petrusevich.volha.homework6;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework6.database.ContactDao;
import com.gmail.petrusevich.volha.homework6.database.Contacts;
import com.gmail.petrusevich.volha.homework6.database.controller.Repository;
import com.gmail.petrusevich.volha.homework6.database.controller.SortedList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class ThreadWithHandler implements Repository {

    private ThreadPoolExecutor threadPoolExecutor;
    private ContactDao contactDao;
    private ContactListAdapter adapter;
    private List<Contacts> listContacts;
    private SortedList sortedList = new SortedList();
    private ContactInfo contactInfo = ContactInfo.getInstance();
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                listContacts = (List<Contacts>) msg.obj;
                adapter.updateListContact(listContacts);
            } else if (msg.what == 1) {
                Contacts contact = (Contacts) msg.obj;
                int position = sortedList.getSortedPosition(listContacts, contact);
                listContacts.add(position, contact);
                adapter.updateListContact(listContacts);
            } else if (msg.what == 2) {
                Contacts contact = (Contacts) msg.obj;
                contactInfo.setNameContact(contact.getName());
                contactInfo.setDataContact(contact.getContactData());
                contactInfo.setIdContact(contact.getId());
            } else if (msg.what == 3) {
                List<Contacts> list = (List<Contacts>) msg.obj;
                adapter.updateListContact(list);
            } else if (msg.what == 4) {
                listContacts.remove(contactInfo.getPosition());
                adapter.updateListContact(listContacts);
            } else if (msg.what == 5) {
                Contacts contactNew = listContacts.get(contactInfo.getPosition());
                contactNew.setName(contactInfo.getNameContact());
                contactNew.setContactData(contactInfo.getDataContact());
                listContacts.remove(contactInfo.getPosition());
                int position = sortedList.getSortedPosition(listContacts, contactNew);
                listContacts.add(position, contactNew);
                adapter.updateListContact(listContacts);
            }
        }
    };


    public ThreadWithHandler(ContactDao contactDao, ContactListAdapter adapter, List<Contacts> listContacts) {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        this.contactDao = contactDao;
        this.adapter = adapter;
        this.listContacts = listContacts;
    }


    @NotNull
    @Override
    public List<Contacts> getAllContacts() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                listContacts = contactDao.getAllContacts();
                handler.sendMessage(handler.obtainMessage(0, listContacts));
            }
        };
        threadPoolExecutor.execute(runnable);
        return listContacts;
    }

    @Override
    public void getContact(final int position) {
        contactInfo.setPosition(position);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String idContact = listContacts.get(position).getId();
                Contacts contact = contactDao.getContact(idContact);
                handler.sendMessage(handler.obtainMessage(2, contact));
            }
        };
        threadPoolExecutor.execute(runnable);
    }

    @Override
    public void getSearchContacts(@Nullable final String search) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Contacts> list = contactDao.getSearchList(search);
                handler.sendMessage(handler.obtainMessage(3, list));
            }
        });
    }

    @Override
    public void insert(@NotNull final Contacts contact) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insertContact(contact);
                handler.sendMessage(handler.obtainMessage(1, contact));
            }
        });
    }

    @Override
    public void delete(@NotNull final String idContact) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Contacts contact = contactDao.getContact(idContact);
                if (contact != null) {
                    contactDao.deleteContact(contact);
                }
                handler.sendMessage(handler.obtainMessage(4));
            }
        });
    }

    @Override
    public void update(@NotNull final String idContact) {
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Contacts contact = contactDao.getContact(idContact);
                if (contact != null) {
                    contact.setName(contactInfo.getNameContact());
                    contact.setContactData(contactInfo.getDataContact());
                    contactDao.updateContact(contact);
                }
                handler.sendMessage(handler.obtainMessage(5));
            }
        });
    }

}