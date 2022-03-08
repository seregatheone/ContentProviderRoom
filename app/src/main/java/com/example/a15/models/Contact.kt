package com.example.a15.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var note : String){
}