package co.heri.exchange.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.heri.exchange.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.country_select_item.view.*
import co.heri.exchange.Model.CountrySelectRecyclerAdapter.CountryHolder


class CountrySelectRecyclerAdapter : RecyclerView.Adapter<CountryHolder>() {

    private var data: List<Country> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        return CountryHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.country_select_item, parent, false)
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

            Glide.with(context)
                    .load(item.flag)
                    .into(flag)

            flag.post {
                entity.text = item.name
                currency.text = item.currency
            }

            setOnClickListener {
                // item: Handle on click
            }
        }
    }
}