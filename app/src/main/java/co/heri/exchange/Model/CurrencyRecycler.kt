package co.heri.exchange.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.currency_item.view.*
import java.util.*
import co.heri.exchange.R
import kotlin.collections.ArrayList


class CurrencyRecycler(private val data: List<Currency>) : RecyclerView.Adapter<CurrencyRecycler.CurrencyHolder>(), Filterable {
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filteredData = data
                } else {
                    if(charString.length >= 2) {
                        val filteredList = ArrayList<Currency>()
                        for (row in data) {

                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.currency!!.toLowerCase().contains(charString.toLowerCase()) || row.alphabeticCode!!.contains(charSequence)) {
                                filteredList.add(row)
                            }
                        }

                        filteredData = filteredList
                    }
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = filteredData
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                filteredData = filterResults.values as ArrayList<Currency>
                notifyDataSetChanged()
            }
        }
    }

    //private var data: List<Currency> = ArrayList()
    private var filteredData: List<Currency> = this.data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        return CurrencyHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.currency_item, parent, false)
        )
    }

    override fun getItemCount() = filteredData.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) = holder.bind(filteredData[position])



    class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Currency) = with(itemView) {

            checked.visibility = View.GONE

            Glide.with(context)
                    .load(item.flagpng!!.toLowerCase())
                    .into(flag)


            // TODO: Bind the data with View
            currency.text = item.alphabeticCode
            name.text = item.currency



            setOnClickListener {
                if(item.selected){
                    checked.visibility = View.VISIBLE
                } else {
                    checked.visibility = View.GONE
                }
                item.selected = !item.selected
            }
        }
    }
}