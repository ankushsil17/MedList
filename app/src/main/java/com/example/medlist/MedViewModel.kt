package com.example.medlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MedViewModel(private val repository: MedRepository) : ViewModel() {


    fun insert(item: MedItems) = GlobalScope.launch {
        repository.insert(item)
    }


    fun delete(item: MedItems) = GlobalScope.launch {
        repository.delete(item)
    }


    fun getAllMedItems() = repository.allMedItems()

}