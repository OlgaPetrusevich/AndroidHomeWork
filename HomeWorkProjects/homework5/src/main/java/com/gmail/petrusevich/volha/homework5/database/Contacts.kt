package com.gmail.petrusevich.volha.homework5.database

import androidx.room.*
import com.gmail.petrusevich.volha.homework5.R

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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Int = 0

    @Ignore
    val image: Int = when (dataType) {
        ContactDataType.PHONE -> R.drawable.contact_phone
        ContactDataType.MAIL -> R.drawable.contact_mail
    }

}