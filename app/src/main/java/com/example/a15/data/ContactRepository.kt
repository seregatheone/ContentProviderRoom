package com.example.a15.data

import androidx.lifecycle.LiveData
import com.example.a15.data.models.Contact

class ContactRepository (private val contactDao : ContactDao){

    val readAllData: LiveData<List<Contact>> = contactDao.readAllContacts()

    suspend fun addNewContact(contact: Contact){
        contactDao.addContact(contact)
    }

    suspend fun updateContact(contact: Contact){
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(contact: Contact){
        contactDao.deleteContact(contact)
    }
    suspend fun deleteAllContact(){
        contactDao.deleteAllContact()
    }

}