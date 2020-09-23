package com.gmail.petrusevich.volha.homework6.database.controller.workthreads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.petrusevich.volha.homework6.R;
import com.gmail.petrusevich.volha.homework6.database.controller.CheckEmptyContactsListKt;
import com.gmail.petrusevich.volha.homework6.database.datacontact.ContactInfo;
import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework6.database.ContactDao;
import com.gmail.petrusevich.volha.homework6.database.datacontact.Contacts;
import com.gmail.petrusevich.volha.homework6.database.controller.Repository;
import com.gmail.petrusevich.volha.homework6.database.controller.SortedList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class ThreadWithHandler implements Repository {

    private ThreadPoolExecutor threadPoolExecutor;
    private ContactDao contactDao;
    private ContactListAdapter adapter;
    private List<Contacts> listContacts;
    private RecyclerView recyclerView;
    private Context context;
    private SortedList sortedList = new SortedList();
    private ContactInfo contactInfo = ContactInfo.getInstance();
    private TextView viewText;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0) {
                listContacts = (List<Contacts>) msg.obj;
                CheckEmptyContactsListKt.isEmpty(adapter, recyclerView, viewText);
                adapter.updateListContact(listContacts);
            } else if (msg.what == 1) {
                Contacts contact = (Contacts) msg.obj;
                int position = sortedList.getSortedPosition(listContacts, contact);
                listContacts.add(position, contact);
                adapter.updateListContact(listContacts);
                CheckEmptyContactsListKt.isEmpty(adapter, recyclerView, viewText);
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
                CheckEmptyContactsListKt.isEmpty(adapter, recyclerView, viewText);
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


    public ThreadWithHandler(ContactDao contactDao, ContactListAdapter adapter, List<Contacts> listContacts, RecyclerView recyclerView, Context context) {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        this.contactDao = contactDao;
        this.adapter = adapter;
        this.listContacts = listContacts;
        this.recyclerView = recyclerView;
        this.context = context;
        viewText = ((Activity)context).findViewById(R.id.viewNoContactText);
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

    @Override
    public void closeThreads() {
        threadPoolExecutor.shutdown();
    }
}