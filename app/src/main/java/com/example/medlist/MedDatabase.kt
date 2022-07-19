package com.example.medlist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MedItems::class], version = 1)
abstract class MedDatabase: RoomDatabase() {

    abstract fun getMedListDao(): MedListDao

    companion object {
        @Volatile
        private var instance: MedDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, MedDatabase::class.java, "MedDatabase.db").build()
    }
}
