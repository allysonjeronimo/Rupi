package com.allysonjeronimo.rupi.ui.main

import androidx.lifecycle.*
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.repository.CurrencyRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository:CurrencyRepository) : ViewModel() {

    private var currencies = MutableLiveData<List<Currency>>()
    private var isLoading = MutableLiveData<Boolean>()
    private var currentCurrency = MutableLiveData<Currency>()

    fun isLoading() = isLoading as LiveData<Boolean>
    fun currencies() = currencies as LiveData<List<Currency>>
    fun currentCurrency() = currentCurrency as LiveData<Currency>

    fun updateCurrentCurrency(currency:Currency){
        currentCurrency.value = currency
    }

    fun getAll() = viewModelScope.launch {
        isLoading.value = true
        currencies.value = repository.allCurrencies()

        if(currencies.value!!.isNotEmpty()){
            currentCurrency.value = currencies.value!![0]
        }
        else{
            // show error
        }

        isLoading.value = false
    }

    class MainViewModelFactory(
        private val repository:CurrencyRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}