package com.allysonjeronimo.rupi.ui.converter

import androidx.lifecycle.*
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.repository.CurrencyRepository
import kotlinx.coroutines.launch

class ConverterViewModel(private val repository:CurrencyRepository) : ViewModel() {

    private var currencies = MutableLiveData<List<Currency>>()
    private var isLoading = MutableLiveData<Boolean>()
    private var isNetworkError = MutableLiveData<Boolean>()
    private var currentCurrency = MutableLiveData<Currency>()

    fun currencies() = currencies as LiveData<List<Currency>>
    fun isLoading() = isLoading as LiveData<Boolean>
    fun isNetworkError() = isNetworkError as LiveData<Boolean>
    fun currentCurrency() = currentCurrency as LiveData<Currency>

    fun updateCurrentCurrency(currency:Currency){
        currentCurrency.value = currency
    }

    fun getAll() = viewModelScope.launch {
        isLoading.value = true
        currencies.value = repository.allCurrencies()

        if(currencies.value!!.isNotEmpty()){
            currentCurrency.value = currencies.value!![0]
            isNetworkError.value = false
        }
        else{
            currentCurrency.value = null
            isNetworkError.value = true
        }

        isLoading.value = false
    }

    class MainViewModelFactory(
        private val repository:CurrencyRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ConverterViewModel(repository) as T
        }
    }
}