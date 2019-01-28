package co.heri.exchange.Model.Retrofit

import co.heri.exchange.Model.Currency
import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @get:GET("/currencies")
    val getcurrencies: Call<List<Currency>>
}