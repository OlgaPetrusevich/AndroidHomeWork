package com.gmail.petrusevich.volha.homework6.database.datacontact

import androidx.room.*
import com.gmail.petrusevich.volha.homework6.R
import com.gmail.petrusevich.volha.homework6.database.ContactDatabaseConverter
import java.util.*

@Entity
class Contacts(

        @ColumnInfo(name = "name")
        var name: String?,

        @ColumnInfo(name = "ContactData")
        var contactData: String?,

        @ColumnInfo(name = "DataType")
        @field:TypeConverters(ContactDatabaseConverter::class)
        var dataType: ContactDataType = ContactDataType.PHONE

) {

    @PrimaryKey
    @ColumnInfo(name = "ID", index = true)
    var id: String = UUID.randomUUID().toString()

    @Ignore
    val image: Int = when (dataType) {
        ContactDataType.PHONE -> R.drawable.contact_phone
        ContactDataType.MAIL -> R.drawable.contact_mail
    }

}