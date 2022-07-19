package com.example.medlist
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MedItems)


    @Delete
    suspend fun delete(item: MedItems)


    @Query("SELECT * FROM med_items")
    fun getAllMedItems(): LiveData<List<MedItems>>
}