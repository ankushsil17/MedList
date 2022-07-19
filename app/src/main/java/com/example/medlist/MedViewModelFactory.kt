package com.example.medlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MedViewModelFactory (private val repository: MedRepository):ViewModelProvider.NewInstanceFactory() {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MedViewModel(repository) as T
    }
}