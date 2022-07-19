package com.example.medlist

class MedRepository(private val db: MedDatabase) {

    suspend fun insert(item: MedItems) = db.getMedListDao().insert(item)
    suspend fun delete(item: MedItems) = db.getMedListDao().delete(item)

    fun allMedItems() = db.getMedListDao().getAllMedItems()
}