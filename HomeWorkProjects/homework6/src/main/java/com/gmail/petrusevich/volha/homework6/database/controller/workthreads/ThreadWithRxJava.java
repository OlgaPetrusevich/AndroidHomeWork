package com.gmail.petrusevich.volha.homework6.database.controller.workthreads;

import com.gmail.petrusevich.volha.homework6.database.datacontact.ContactInfo;
import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter;
import com.gmail.petrusevich.volha.homework6.database.ContactDao;
import com.gmail.petrusevich.volha.homework6.database.datacontact.Contacts;
import com.gmail.petrusevich.volha.homework6.database.controller.Repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThreadWithRxJava implements Repository {

    private ContactDao contactDao;
    private ContactListAdapter adapter;
    private List<Contacts> listContacts;
    private ContactInfo contactInfo = ContactInfo.getInstance();
    private CompositeDisposable disposables = new CompositeDisposable();


    public ThreadWithRxJava(ContactDao contactDao, ContactListAdapter adapter, List<Contacts> listContacts) {
        this.contactDao = contactDao;
        this.adapter = adapter;
        this.listContacts = listContacts;
    }

    @NotNull
    @Override
    public List<Contacts> getAllContacts() {
        disposables.add(Observable.create(new ObservableOnSubscribe<List<Contacts>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Contacts>> emitter) throws Exception {
                listContacts = contactDao.getAllContacts();
                emitter.onNext(listContacts);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contacts>>() {
                    @Override
                    public void accept(List<Contacts> contacts) throws Exception {
                        adapter.updateListContact(contacts);
                    }
                }));
        return listContacts;
    }

    @Override
    public void getContact(final int position) {
        disposables.add(Observable.create(new ObservableOnSubscribe<Contacts>() {
            @Override
            public void subscribe(ObservableEmitter<Contacts> emitter) throws Exception {
                String id = listContacts.get(position).getId();
                emitter.onNext(contactDao.getContact(id));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Contacts>() {
                    @Override
                    public void accept(Contacts contacts) throws Exception {
                        contactInfo.setNameContact(contacts.getName());
                        contactInfo.setDataContact(contacts.getContactData());
                        contactInfo.setIdContact(contacts.getId());
                    }
                }));
    }

    @Override
    public void getSearchContacts(@Nullable final String search) {
        disposables.add(Observable.create(new ObservableOnSubscribe<List<Contacts>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Contacts>> emitter) throws Exception {
                emitter.onNext(contactDao.getSearchList(search));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contacts>>() {
                    @Override
                    public void accept(List<Contacts> contacts) throws Exception {
                        adapter.updateListContact(contacts);
                    }
                }));
    }

    @Override
    public void insert(@NotNull final Contacts contact) {
        disposables.add(Observable.create(new ObservableOnSubscribe<List<Contacts>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Contacts>> emitter) throws Exception {
                contactDao.insertContact(contact);
                emitter.onNext(contactDao.getAllContacts());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contacts>>() {
                    @Override
                    public void accept(List<Contacts> contacts) throws Exception {
                        listContacts = contacts;
                        adapter.updateListContact(contacts);
                    }
                }));
    }

    @Override
    public void delete(@NotNull final String idContact) {
        disposables.add(Observable.create(new ObservableOnSubscribe<List<Contacts>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Contacts>> emitter) throws Exception {
                Contacts contact = contactDao.getContact(idContact);
                if (contact != null) {
                    contactDao.deleteContact(contact);
                    emitter.onNext(contactDao.getAllContacts());
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contacts>>() {
                    @Override
                    public void accept(List<Contacts> contacts) throws Exception {
                        listContacts = contacts;
                        adapter.updateListContact(listContacts);
                    }
                }));
    }

    @Override
    public void update(@NotNull final String idContact) {
        disposables.add(Observable.create(new ObservableOnSubscribe<List<Contacts>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Contacts>> emitter) throws Exception {
                Contacts contact = contactDao.getContact(idContact);
                if (contact != null) {
                    contact.setName(contactInfo.getNameContact());
                    contact.setContactData(contactInfo.getDataContact());
                    contactDao.updateContact(contact);
                }
                emitter.onNext(contactDao.getAllContacts());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contacts>>() {
                    @Override
                    public void accept(List<Contacts> contacts) throws Exception {
                        listContacts = contacts;
                        adapter.updateListContact(listContacts);
                    }
                }));


    }

    @Override
    public void closeThreads() {
        disposables.dispose();
    }
}
