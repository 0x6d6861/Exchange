package co.heri.exchange.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.heri.exchange.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.currency_item.view.*
import java.util.*


class CurrencyRecycler : RecyclerView.Adapter<CurrencyRecycler.CurrencyHolder>() {

    private var data: List<Currency> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        return CurrencyHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.currency_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<Currency>) {
        this.data = data
        notifyDataSetChanged()
    }

    class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Currency) = with(itemView) {


            Glide.with(context)
                    .load(item.flagpng!!.toLowerCase())
                    .into(flag)


            // TODO: Bind the data with View
            currency.text = item.alphabeticCode
            name.text = item.currency
            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}