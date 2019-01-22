package co.heri.exchange.Model

import android.app.PendingIntent.getActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.heri.exchange.MainActivity
import java.util.*
import co.heri.exchange.Model.CountryRecyclerAdapter.CountryHolder
import kotlinx.android.synthetic.main.exhange_item.view.*
import co.heri.exchange.R

class CountryRecyclerAdapter : RecyclerView.Adapter<CountryHolder>() {

    private var data: List<Country> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.exhange_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CountryHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<Country>) {
        this.data = data
        notifyDataSetChanged()
    }

    class CountryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Country) = with(itemView) {
            // TODO: Bind the data with View
            val resId = resources.getIdentifier("flag_" + item.iso.toLowerCase(), "drawable", itemView.context.packageName)

            country_flag.setImageResource(resId)

            country_flag.post {
                country.text = item.name
                currency.text = item.currency
                amount.text = item.amount
            }

            setOnClickListener {
                // item: Handle on click
            }
        }
    }
}