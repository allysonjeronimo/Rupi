package com.allysonjeronimo.rupi.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.data.db.entity.Currency
import kotlinx.android.synthetic.main.currencies_dialog_fragment.*

class CurrenciesDialogFragment : DialogFragment() {

    private lateinit var currencies:List<Currency>
    private lateinit var onClickListener:(Currency) -> Unit

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
        recycler_currencies.adapter = CurrencyAdapter(currencies) { currency ->
            onClickListener(currency)
            dialog?.dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    companion object{

        val TAG = CurrenciesDialogFragment::class.simpleName

        fun newInstance(currencies:List<Currency>, onClickListener:(Currency) -> Unit) : CurrenciesDialogFragment{
            val instance = CurrenciesDialogFragment()
            instance.currencies = currencies
            instance.onClickListener = onClickListener
            return instance
        }
    }
}