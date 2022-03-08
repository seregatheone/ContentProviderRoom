package com.example.a15.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.a15.models.Contact

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact: Contact)

    @Query("SELECT * FROM contact_table ORDER by id ASC")
    fun readAllContacts(): LiveData<List<Contact>>

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}