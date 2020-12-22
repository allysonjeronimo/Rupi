package com.allysonjeronimo.rupi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.data.remote.AwesomeApi
import com.allysonjeronimo.rupi.repository.CurrencyDataRepository
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
    }

    private fun createViewModel(){
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://economia.awesomeapi.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val repository = CurrencyDataRepository(retrofit.create(AwesomeApi::class.java))

        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(repository)
        ).get(MainViewModel::class.java)
    }

    private fun observeEvents(){
        viewModel.currencies().observe(this.viewLifecycleOwner, {currencies ->
            button_currency.text = currencies[0].name
            text_currency_1.text = currencies[0].defaulValue()
            text_currency_2.text = currencies[0].quotation()
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

    override fun onStart() {
        super.onStart()
        viewModel.getAll()
    }

}