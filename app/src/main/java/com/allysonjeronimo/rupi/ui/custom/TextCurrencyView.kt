package com.allysonjeronimo.rupi.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.allysonjeronimo.rupi.R

class TextCurrencyView(
    context: Context,
    attrs:AttributeSet
    ) : LinearLayout(context, attrs){

    private var textSymbol: TextView? = null
    private var textValue: EditText? = null

    init{

        val attrsArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TextCurrency,
            0, 0
        )

        val symbol = attrsArray.getString(R.styleable.TextCurrency_symbol)
        val value = attrsArray.getFloat(R.styleable.TextCurrency_value, 0.0f)

        attrsArray.recycle()

        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_HORIZONTAL

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_text_currency, this, true)

        textSymbol = getChildAt(0) as TextView
        textValue = getChildAt(1) as EditText

        symbol?.let{
            setSymbol(symbol)
        }

        setValue(value.toDouble())
    }

    fun setValue(value:Double){
        this.textValue?.setText(value.toString())
    }

    fun setSymbol(symbol:String){
        textSymbol?.text = symbol
    }
}