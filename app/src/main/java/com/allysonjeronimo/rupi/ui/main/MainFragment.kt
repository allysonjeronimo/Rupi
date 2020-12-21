package com.allysonjeronimo.rupi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.model.remote.AwesomeApi
import com.allysonjeronimo.rupi.model.repository.CurrencyDataRepository
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

        viewModel.currencies().observe(this.viewLifecycleOwner, {currencies ->
            button_currency.text = currencies[0].name
        })
    }

    override fun onStart() {
        super.onStart()

        viewModel.getAll()
    }

}