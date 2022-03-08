package com.example.a15.fragments

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.a15.data.ContactDatabase
import com.example.a15.data.ContactRepository
import com.example.a15.data.ContentProvider
import com.example.a15.models.Contact
import kotlinx.coroutines.*

class StartFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Contact>>
    private val repository: ContactRepository
    private val contactProvider by lazy{ ContentProvider(application.applicationContext) }

    private var contentProviderData : List<Contact>
    private var repositoryDataValue : List<Contact>

    private var contactIntersection : Set<Contact>
    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)

        repositoryDataValue = when (repository.readAllData.value) {
            null -> emptyList()
            else -> repository.readAllData.value!!
        }
        contentProviderData = when (contactProvider.allContactList.value) {
            null -> emptyList()
            else -> contactProvider.allContactList.value!!
        }

        contactIntersection= contentProviderData.intersect(repositoryDataValue as Iterable<Contact>)

        contentProviderData
            .filter { it !in contactIntersection }
            .forEach { addNewContact(it)}

        readAllData = repository.readAllData

    }
    private fun addNewContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNewContact(contact)
        }
    }

    private fun deleteContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteContact(contact)
        }
    }

    fun updateContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateContact(contact)
        }
    }

}