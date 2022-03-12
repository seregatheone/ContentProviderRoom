package com.example.a15.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "result_contact_table")
@Parcelize
data class Contact (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String,
    var note : String) : Parcelable
