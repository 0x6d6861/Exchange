package co.heri.exchange

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.room.Room
import co.heri.exchange.Model.*
import co.heri.exchange.Model.Dao.AppDatabase
import co.heri.exchange.Model.Retrofit.Api
import co.heri.exchange.Model.Retrofit.RateRequest
import co.heri.exchange.Model.Retrofit.ServiceClient
import com.google.gson.JsonObject
import kotlin.concurrent.thread
import kotlinx.android.synthetic.main.currency_select.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var savedCurrencies: MutableList<Currency>
    private lateinit var list_country_adapter: CountryRecyclerAdapter
    private lateinit var select_country_adapter: CountrySelectRecyclerAdapter
    private lateinit var selectedCurrency: Currency
    private lateinit var currencyApi: Api



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.currencyApi = (ServiceClient.getRetrofitInstance(this))!!.create(Api::class.java)



        list_currencies.layoutManager = LinearLayoutManager(this);
        this.list_country_adapter = CountryRecyclerAdapter()


        list_currencies.addItemDecoration(DividerItemDecoration(this, 0))
        fab.setOnClickListener { view ->
            startActivity(Intent(this, CurrencyActivity::class.java))
        }

        change_link.setOnClickListener { view ->
            showDiag(this);
        }
    }

    override fun onDestroy() {
        this.database.close()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()

        thread {


            // DBHelper(this).readableDatabase
            this.database = Room.databaseBuilder(applicationContext,
                    AppDatabase::class.java, "database.db")
                    .fallbackToDestructiveMigration()
                    .build()

            this.savedCurrencies = this.database.currencyDao().getCurrencies() as MutableList<Currency>

            val currencies: MutableList<Country> = mutableListOf<Country>();


            if(this.savedCurrencies.size > 0){
                this.selectedCurrency = savedCurrencies.find { it.selected } ?: savedCurrencies.first()
                this.savedCurrencies.remove(selectedCurrency);
                runOnUiThread {
                    currency_desc.text = "${selectedCurrency.alphabeticCode} - ${selectedCurrency.entity}"
                }
            }


            for(currency in savedCurrencies){
                currencies.add(Country(name = currency.entity ?: "Not set", currency = currency.alphabeticCode, amount = "658.45", flag = currency.flagpng ?: "No flag"))
            }

            this.updateRates()
            //Log.e("SAVED_", currencies.toString())

            runOnUiThread {
                list_country_adapter.swapData(currencies)
                list_currencies.adapter = list_country_adapter

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showDiag(context: Context) {


        val builder = AlertDialog.Builder(context, R.style.AppTheme_Dialog)

        val dialogLayout = layoutInflater.inflate(R.layout.currency_select, null)

        builder.setView(dialogLayout)

        val currencies: MutableList<Country> = mutableListOf<Country>()

        for(currency in savedCurrencies){
            currencies.add(Country(name = currency.entity ?: "Not set", currency = currency.alphabeticCode, amount = "658.45", flag = currency.flagpng ?: "No flag"))
        }

        dialogLayout.select_country.layoutManager = LinearLayoutManager(this);
        this.select_country_adapter = CountrySelectRecyclerAdapter();

        runOnUiThread {
            select_country_adapter.swapData(currencies)
            dialogLayout.select_country.adapter = select_country_adapter
        }


        builder.setTitle("Select Currency")
                .setNegativeButton("Cancel"
                ) { dialog, id ->
                    // mListener.onDialogNegativeClick(this)
                    dialog.cancel()
                }

        builder.create()
        builder.show()

    }

    private fun updateRates(){

            for (currency in this.savedCurrencies){

                val call = this.currencyApi.getRate(RateRequest(from = this.selectedCurrency.alphabeticCode, to = currency.alphabeticCode))

                call.enqueue(object : Callback<Rate> {
                    override fun onResponse(call: Call<Rate>, response: Response<Rate>?) {
                        if (response != null) {
                            val rate  = response.body()
                            if(rate != null){
                                Log.i("RATES_", rate.toString())
                                thread {
                                    database.rateDao().insert(rate)
                                }
                            }
                        }

                    }

                    override fun onFailure(call: Call<Rate>, t: Throwable) {
                        Log.e("ERROR_", t.toString());

                    }
                })

            }

    }
}
