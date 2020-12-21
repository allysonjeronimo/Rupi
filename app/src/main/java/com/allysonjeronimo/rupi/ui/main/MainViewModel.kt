package com.allysonjeronimo.rupi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allysonjeronimo.rupi.model.entity.Currency
import com.allysonjeronimo.rupi.model.repository.CurrencyRepository

class MainViewModel(private val repository:CurrencyRepository) : ViewModel() {

    private var currencies = MutableLiveData<List<Currency>>()

    fun currencies() = currencies as LiveData<List<Currency>>

    fun getAll(){
        repository.getAll({ list -> currencies.value = list
        }, {

        })
    }

    class MainViewModelFactory(
        private val repository:CurrencyRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}