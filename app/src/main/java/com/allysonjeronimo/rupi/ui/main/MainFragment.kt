package com.allysonjeronimo.rupi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.data.db.AppDatabase
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.data.network.ApiService
import com.allysonjeronimo.rupi.extensions.FORMAT_TIME_HOURS_MINUTES
import com.allysonjeronimo.rupi.extensions.resourceId
import com.allysonjeronimo.rupi.repository.CurrencyDataRepository
import com.allysonjeronimo.rupi.ui.currencies.CurrenciesDialogFragment
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createViewModel()
        observeEvents()
        setListeners()
    }

    private fun createViewModel(){

        val remoteService = ApiService.getInstance(requireContext())
        val cacheService = AppDatabase.getInstance(requireContext()).currencyDAO()

        val repository = CurrencyDataRepository(cacheService, remoteService)

        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(repository)
        ).get(MainViewModel::class.java)
    }

    private fun observeEvents(){
        viewModel.currentCurrency().observe(this.viewLifecycleOwner, {
            currentCurrency -> showValues(currentCurrency)
        })

        viewModel.isLoading().observe(this.viewLifecycleOwner, {
                isLoading ->
            if(isLoading)
                showProgress()
            else
                hideProgress()
        })
    }

    private fun showValues(currentCurrency:Currency){
        button_currency.setCompoundDrawablesWithIntrinsicBounds(
            requireContext().resourceId(currentCurrency.icon()),
            0,
            R.drawable.ic_filled_arrow_down,
            0
        )
        button_currency.text = currentCurrency.name
        /*
        text_currency_1.text = currentCurrency.defaultValue()
        text_currency_2.text = currentCurrency.quotation()
        text_variation.text = currentCurrency.variation()
        text_last_update.text = currentCurrency.date.toString(FORMAT_TIME_HOURS_MINUTES)

        if(currentCurrency.pctChange > 0.0){
            image_up.visibility = View.VISIBLE
            image_down.visibility = View.GONE
            image_flat.visibility = View.GONE
        }
        else if(currentCurrency.pctChange < 0.0){
            image_up.visibility = View.GONE
            image_down.visibility = View.VISIBLE
            image_flat.visibility = View.GONE
        }
        else{
            image_up.visibility = View.GONE
            image_down.visibility = View.GONE
            image_flat.visibility = View.VISIBLE
        }*/
    }

    private fun showProgress(){
        progress_loading.visibility = View.VISIBLE
        group_view.visibility = View.GONE
    }

    private fun hideProgress(){
        progress_loading.visibility = View.GONE
        group_view.visibility = View.VISIBLE
    }

    private fun setListeners() {
        button_currency.setOnClickListener {
            CurrenciesDialogFragment
                .newInstance(viewModel.currencies().value ?: listOf<Currency>()) { currency ->
                    viewModel.updateCurrentCurrency(currency)
                }
                .show(activity!!.supportFragmentManager, CurrenciesDialogFragment.TAG)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAll()
    }

}