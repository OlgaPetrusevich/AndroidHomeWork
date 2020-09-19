package com.gmail.petrusevich.volha.homework6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework6.adapter.ContactListAdapter
import com.gmail.petrusevich.volha.homework6.adapter.OnContactListener
import com.gmail.petrusevich.volha.homework6.database.ContactDao
import com.gmail.petrusevich.volha.homework6.database.ContactDatabase
import com.gmail.petrusevich.volha.homework6.database.Contacts
import com.gmail.petrusevich.volha.homework6.database.controller.Repository
import com.gmail.petrusevich.volha.homework6.database.controller.SearchContact
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, OnContactListener {

    private var listContacts: List<Contacts>? = ArrayList()
    private val searchContact: SearchContact = SearchContact()
    private val contactDao: ContactDao? by lazy { ContactDatabase.getInstance(this)?.getContactDao() }
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.viewListContact) }
    private lateinit var repository: Repository
    private val databaseController = DatabaseController()
    private val contactListAdapter by lazy { ContactListAdapter(listContacts, this) }
    private val threadCreation = ThreadCreation()
    private var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewAddButton.setOnClickListener(this)
        recyclerView.adapter = contactListAdapter
        repository = threadCreation.createThreadWork(itemPosition, contactDao, contactListAdapter, listContacts)
        listContacts = repository.getAllContacts()
        searchContact.searchContact(viewSearchContact, contactListAdapter, repository)
        viewSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, itemView: View?, position: Int, id: Long) {
                itemPosition = position
                repository = threadCreation.createThreadWork(position, contactDao, contactListAdapter, listContacts)
            }
        }

//        isEmpty(contactListAdapter, recyclerView, viewNoContactText)
    }


    override fun onClick(view: View?) {
        if (view == viewAddButton) {
            startActivityForResult(AddContactActivity.newIntent(this), 1)
        }
    }

    override fun onContactClick(position: Int) {
        databaseController.showContactInfo(repository, position)
        startActivityForResult(EditContactActivity.newIntent(this), 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            databaseController.addContact(repository, data)
        } else if (requestCode == 2) {
            val isRemove = data?.getBooleanExtra(KEY_ACTION, false)
            if (isRemove != null) {
                databaseController.actionContact(repository, isRemove)
            }
        }
    }


}
