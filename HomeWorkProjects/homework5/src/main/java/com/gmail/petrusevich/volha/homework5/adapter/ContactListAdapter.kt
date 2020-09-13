package com.gmail.petrusevich.volha.homework5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.petrusevich.volha.homework5.R
import com.gmail.petrusevich.volha.homework5.database.Contacts

class ContactListAdapter(
        private var listContacts: List<Contacts>? = ArrayList(),
        private val onContactListener: OnContactListener) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view, onContactListener)
    }

    override fun getItemCount(): Int {
        return if (listContacts?.size == null) {
            0
        } else {
            listContacts?.size!!
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listContacts?.get(position))
    }

    fun updateListContact(contacts: List<Contacts>?) {
        listContacts = contacts
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val onContactListener: OnContactListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val itemName: TextView = itemView.findViewById(R.id.viewTextName)
        private val itemData: TextView = itemView.findViewById(R.id.viewContactData)
        private val itemImage: ImageView = itemView.findViewById(R.id.viewImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(contact: Contacts?) {
            itemName.text = contact?.name
            itemData.text = contact?.contactData
            itemImage.setImageResource(contact?.image!!)
        }

        override fun onClick(view: View?) {
            onContactListener.onContactClick(adapterPosition)
        }
    }

}