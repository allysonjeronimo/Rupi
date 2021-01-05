package com.allysonjeronimo.rupi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.data.entity.Currency
import com.allysonjeronimo.rupi.data.remote.AwesomeApi
import com.allysonjeronimo.rupi.extensions.FORMAT_TIME_HOURS_MINUTES
import com.allysonjeronimo.rupi.extensions.resourceId
import com.allysonjeronimo.rupi.extensions.toString
import com.allysonjeronimo.rupi.repository.CurrencyDataRepository
import com.allysonjeronimo.rupi.ui.currencies.CurrenciesDialogFragment
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createViewModel()
        observeEvents()
        setListeners()
    }

    private fun createViewModel(){
        val retrofit = Retrofit
            .Builder()
            .baseUrl(resources.getString(R.string.base_url_awesome_api))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val repository = CurrencyDataRepository(retrofit.create(AwesomeApi::class.java))

        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(repository)
        ).get(MainViewModel::class.java)
    }

    private fun observeEvents(){
        viewModel.currentCurrency().observe(this.viewLifecycleOwner, {
            currentCurrency ->
            button_currency.setCompoundDrawablesWithIntrinsicBounds(
                requireContext().resourceId(currentCurrency.icon()),
                0,
                R.drawable.ic_filled_arrow_down,
                0
            )
            button_currency.text = currentCurrency.name
            text_currency_1.text = currentCurrency.defaultValue()
            text_currency_2.text = currentCurrency.quotation()
            text_variation.text = currentCurrency.variation()
            image_arrow_up.visibility = if(currentCurrency.pctChange >= 0.0) View.VISIBLE else View.GONE
            image_arrow_down.visibility = if(currentCurrency.pctChange < 0.0) View.VISIBLE else View.GONE
            text_last_update.text = currentCurrency.date.toString(FORMAT_TIME_HOURS_MINUTES)
        })

        viewModel.isLoading().observe(this.viewLifecycleOwner, {
                isLoading ->
            if(isLoading)
                showProgress()
            else
                hideProgress()
        })
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