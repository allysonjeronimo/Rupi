package com.allysonjeronimo.rupi.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.data.entity.Currency
import kotlinx.android.synthetic.main.currencies_dialog_fragment.*

class CurrenciesDialogFragment : DialogFragment() {

    private lateinit var currencies:List<Currency>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currencies_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_currencies.layoutManager = LinearLayoutManager(requireContext())
        recycler_currencies.adapter = CurrencyAdapter(currencies)
    }

    companion object{

        val TAG = CurrenciesDialogFragment::class.simpleName

        fun newInstance(currencies:List<Currency>) : CurrenciesDialogFragment{
            val instance = CurrenciesDialogFragment()
            instance.currencies = currencies
            return instance
        }
    }
}