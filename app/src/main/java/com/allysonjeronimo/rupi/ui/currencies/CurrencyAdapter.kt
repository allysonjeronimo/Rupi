package com.allysonjeronimo.rupi.ui.currencies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allysonjeronimo.rupi.R
import com.allysonjeronimo.rupi.data.entity.Currency
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrencyAdapter(
    private val currencies:List<Currency>
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(currencies[position])

    override fun getItemCount() = currencies.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val imageIcon = itemView.image_icon
        private val textName = itemView.text_view_name

        fun bind(currency:Currency){
            textName.text = currency.name
        }
    }
}