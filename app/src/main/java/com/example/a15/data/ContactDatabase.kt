package com.example.a15.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a15.models.Contact

@Database(entities = [Contact::class],version = 1,exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao() : ContactDao

    companion object{
        @Volatile
        private var INSTANCE : ContactDatabase? = null

        fun getDatabase(context: Context) : ContactDatabase{
            val tempInstance = INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contact_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}