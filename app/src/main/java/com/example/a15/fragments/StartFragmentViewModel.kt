package com.example.a15.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.a15.data.ContactDatabase
import com.example.a15.data.ContactRepository
import com.example.a15.data.ContentProvider
import com.example.a15.data.models.Contact
import com.example.a15.intersect
import kotlinx.coroutines.*

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Contact>>
    private val repository: ContactRepository

    private val contactProvider by lazy{ ContentProvider(application.applicationContext) }
    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
//        deleteAllContact()
        readAllData = repository.readAllData
    }

    private fun addNewContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNewContact(contact)
        }
    }
//
//    clear db if needed
//    fun deleteContact(contact: Contact){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deleteContact(contact)
//        }
//    }

    fun updateContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateContact(contact)
        }
    }
    private fun deleteAllContact(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllContact()
        }
    }
    fun workWithTable(listOfContacts: List<Contact>) {
        val contentProviderData = when (contactProvider.allContactList.value) {
            null -> emptyList()
            else -> contactProvider.allContactList.value!!
        }

        val contactIntersection = intersect(contentProviderData,listOfContacts)
        contentProviderData
            .filter { it !in contactIntersection }
            .forEach {
                addNewContact(it)
            }

    }

}