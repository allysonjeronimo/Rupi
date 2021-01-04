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
            button_currency.text = currentCurrency.name
            text_currency_1.text = currentCurrency.defaulValue()
            text_currency_2.text = currentCurrency.quotation()
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
                .newInstance(viewModel.currencies().value ?: listOf<Currency>())
                .show(activity!!.supportFragmentManager, CurrenciesDialogFragment.TAG)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAll()
    }

}