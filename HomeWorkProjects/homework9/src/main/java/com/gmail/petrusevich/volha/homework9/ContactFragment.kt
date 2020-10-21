package com.gmail.petrusevich.volha.homework9

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactFragment : Fragment() {

    private val URI_PATH = "content://com.gmail.petrusevich.volha.homework5/data/data"

    private var contactsList = mutableListOf<ContactDataModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_contacts, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewListRecycler.adapter = ContactListAdapter(contactsList)
        val cursor = activity?.contentResolver?.query(Uri.parse(URI_PATH), null, null, null, null)
        if (cursor != null) {
            val nameIndex = cursor.getColumnIndex("name")
            val dataIndex = cursor.getColumnIndex("ContactData")
            while (cursor.moveToNext()) {
                contactsList.add(ContactDataModel(cursor.getString(nameIndex), cursor.getString(dataIndex)))
            }
            cursor.close()
        }
        (viewListRecycler.adapter as ContactListAdapter).updateListContact(contactsList)
//        with(viewLifecycleOwner) {
//            cityListViewModel.cityListLiveData.observe(this, Observer { items ->
//                (viewCityList.adapter as? CityListAdapter)?.updateCityList(items)
//
//            })
//            cityListViewModel.cityErrorLiveData.observe(this, Observer { throwable ->
//                Toast.makeText(activity?.applicationContext, throwable.message, Toast.LENGTH_SHORT).show()
//            })
//        }
//        cityListViewModel.getCityList()

    }

    companion object {
        const val TAG = "ContactFragment"
        fun gteInstance() = ContactFragment()
    }
}