package com.example.a15.data

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.a15.models.Contact

@SuppressLint("Range")
class ContentProvider (context: Context){

    private val _cursor by lazy { context.contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null)}
    private val cursor = _cursor!!

    val allContactList = MutableLiveData<List<Contact>>(emptyList())

    init {
        val innerContactList = mutableListOf<Contact>()
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                var name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                if (name.isNullOrEmpty()) name = "123"
                innerContactList.add(Contact(0,name,""))
            }
            _cursor?.close()
            allContactList.value = innerContactList
        }
    }

}