package com.allysonjeronimo.rupi.ui.converter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.data.db.AppDatabase
import com.allysonjeronimo.rupi.data.db.entity.Currency
import com.allysonjeronimo.rupi.data.network.ApiService
import com.allysonjeronimo.rupi.extensions.FORMAT_TIME_HOURS_MINUTES
import com.allysonjeronimo.rupi.extensions.resourceId
import com.allysonjeronimo.rupi.extensions.toString
import com.allysonjeronimo.rupi.repository.CurrencyDataRepository
import com.allysonjeronimo.rupi.ui.currencies.CurrenciesDialogFragment
import kotlinx.android.synthetic.main.converter_fragment.*
import kotlinx.android.synthetic.main.offline_layout.*

class ConverterFragment : Fragment(R.layout.converter_fragment) {

    companion object {
        fun newInstance() = ConverterFragment()
    }

    private lateinit var viewModel: ConverterViewModel

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
            ConverterViewModel.MainViewModelFactory(repository)
        ).get(ConverterViewModel::class.java)
    }

    private fun observeEvents(){
        viewModel.currentCurrency().observe(this.viewLifecycleOwner, {
            currentCurrency ->
            if(currentCurrency != null)
                showValues(currentCurrency)
            else
                showNetworkError()
        })

        viewModel.isLoading().observe(this.viewLifecycleOwner, {
                isLoading -> showProgress(isLoading)
        })
    }

    private fun showNetworkError() {
        group_view.visibility = View.GONE
        layout_offline.visibility = View.VISIBLE
    }

    private fun showValues(currentCurrency:Currency){

        group_view.visibility = View.VISIBLE
        layout_offline.visibility = View.GONE

        button_currency.setCompoundDrawablesWithIntrinsicBounds(
            requireContext().resourceId(currentCurrency.icon()),
            0,
            R.drawable.ic_filled_arrow_down,
            0
        )
        button_currency.text = currentCurrency.name

        //text_currency_1.text = currentCurrency.defaultValue()
        //text_currency_2.text = currentCurrency.quotation()
        //text_variation.text = currentCurrency.variation()
        text_last_update.text = currentCurrency.lastPrice.date.toString(FORMAT_TIME_HOURS_MINUTES)

        if(currentCurrency.lastPrice.pctChange > 0.0){
            image_up.visibility = View.VISIBLE
            image_down.visibility = View.GONE
            image_flat.visibility = View.GONE
        }
        else if(currentCurrency.lastPrice.pctChange < 0.0){
            image_up.visibility = View.GONE
            image_down.visibility = View.VISIBLE
            image_flat.visibility = View.GONE
        }
        else{
            image_up.visibility = View.GONE
            image_down.visibility = View.GONE
            image_flat.visibility = View.VISIBLE
        }
    }

    private fun showProgress(show:Boolean){
        progress_loading.visibility = if(show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setListeners() {
        button_currency.setOnClickListener {
            CurrenciesDialogFragment
                .newInstance(viewModel.currencies().value ?: listOf<Currency>()) { currency ->
                    viewModel.updateCurrentCurrency(currency)
                }
                .show(activity!!.supportFragmentManager, CurrenciesDialogFragment.TAG)
        }
        button_try_again.setOnClickListener {
            layout_offline.visibility = View.GONE
            viewModel.getAll()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAll()
    }

}