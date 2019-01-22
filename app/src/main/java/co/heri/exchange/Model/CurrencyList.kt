package co.heri.exchange.Model

import com.google.gson.annotations.SerializedName

data class CurrencyList(

	@field:SerializedName("Currencies")
	val currencies: List<Currency>? = null
)