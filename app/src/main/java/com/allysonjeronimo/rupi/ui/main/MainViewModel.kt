package com.allysonjeronimo.rupi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.allysonjeronimo.rupi.data.entity.Currency
import com.allysonjeronimo.rupi.data.repository.CurrencyRepository

class MainViewModel(private val repository:CurrencyRepository) : ViewModel() {

    private var currencies = MutableLiveData<List<Currency>>()
    private var isLoading = MutableLiveData<Boolean>()
    private var currentCurrency = MutableLiveData<Currency>()

    fun currencies() = currencies as LiveData<List<Currency>>
    fun isLoading() = isLoading as LiveData<Boolean>
    fun currentCurrency() = currentCurrency as LiveData<Currency>

    fun getAll(){
        isLoading.value = true
        repository.getAll(
            {
                list -> currencies.value = list
                isLoading.value = false
            },
            {
                isLoading.value = false
            }
        )
    }

    class MainViewModelFactory(
        private val repository:CurrencyRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}