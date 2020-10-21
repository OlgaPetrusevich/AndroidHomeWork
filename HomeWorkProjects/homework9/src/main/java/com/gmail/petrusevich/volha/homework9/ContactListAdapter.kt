package com.gmail.petrusevich.volha.homework9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactListAdapter(
        private var contactsList: List<ContactDataModel> = mutableListOf()
) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    fun updateListContact(contacts: List<ContactDataModel>) {
//        contactsList.apply {
//            clear()
//            addAll(contacts)
//        }
        contactsList = contacts
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(contact: ContactDataModel) {
            itemView.viewTextName.text = contact.name
            itemView.viewContactData.text = contact.contactData
//            itemView.viewImage.setImageResource(contact.image!!)
        }

    }
}

