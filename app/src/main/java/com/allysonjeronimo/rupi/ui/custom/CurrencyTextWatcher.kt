package com.allysonjeronimo.rupi.ui.custom

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class CurrencyTextWatcher(editText:EditText) : TextWatcher{

    private var editTextWeakReference:WeakReference<EditText>? = null
    private var locale = Locale.getDefault()

    init{
        editTextWeakReference = WeakReference(editText)
    }

    override fun beforeTextChanged(s:CharSequence,start:Int,count:Int,after:Int) {}

    override fun onTextChanged(s:CharSequence,start:Int,count:Int,after:Int) {}

    override fun afterTextChanged(editable: Editable?) {
        editTextWeakReference?.get()?.let{
            it.removeTextChangedListener(this)

            val parsed = parseToBigDecimal(editable.toString())
            val formatted = NumberFormat.getCurrencyInstance(locale).format(parsed)
            val replaceable = String.format("[%s\\s]", getCurrencySymbol())
            val cleanString = formatted.replace(replaceable, "")

            it.setText(cleanString)
            it.setSelection(cleanString.length)
            it.addTextChangedListener(this)
        }
    }

    private fun parseToBigDecimal(value:String): BigDecimal{
        val replaceable = String.format("[%s\\s]", getCurrencySymbol())
        val cleanString = value.replace(replaceable, "")

        return try{
            BigDecimal(cleanString)
                .setScale(2, BigDecimal.ROUND_FLOOR)
                .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
        }catch(ex:NumberFormatException){
            BigDecimal(0)
        }
    }

    private fun getCurrencySymbol() =
        NumberFormat.getCurrencyInstance(locale).currency.symbol

}