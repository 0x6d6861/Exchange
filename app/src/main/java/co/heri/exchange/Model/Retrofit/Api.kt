package co.heri.exchange.Model.Retrofit

import co.heri.exchange.Model.Currency
import co.heri.exchange.Model.Rate
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST




interface Api {

    @get:GET("/currencies")
    val getcurrencies: Call<List<Currency>>

    @POST("/getrate")
    @Headers("Content-Type: application/json")
    fun getRate(@Body raterequest: RateRequest): Call<Rate>
}