package co.heri.exchange

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.heri.exchange.Model.Country
import co.heri.exchange.Model.CountryRecyclerAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import co.heri.exchange.Model.Dao.DBHelper
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        thread {
            DBHelper(this).readableDatabase
        }

        var currencies = listOf<Country>(
                Country(name = "United Kingdom Pound", iso = "GB", currency = "GBP", amount = "658.45"),
//                Country(name = "Hong Kong dollar", iso = "HK", currency = "HKD", amount = "854.78"),
//                Country(name = "Canadian dollar", iso = "CA", currency = "CAD", amount = "258.13"),
                Country(name = "United Arab Emirates dirham", iso = "AE", currency = "AED", amount = "789.58"),
//                Country(name = "Japanese yen", iso = "JP", currency = "JPY", amount = "369.12"),
                Country(name = "Australian dollar", iso = "AU", currency = "AUD", amount = "489.78"),
                Country(name = "Russian ruble", iso = "RU", currency = "RUB", amount = "489.78"),
                Country(name = "United States dollar", iso = "US", currency = "USD", amount = "659.05")
        )

        var list_country_adapter = CountryRecyclerAdapter()


        list_currencies.layoutManager = LinearLayoutManager(this);
        list_currencies.adapter = list_country_adapter

        list_country_adapter.swapData(currencies)

        list_currencies.addItemDecoration(DividerItemDecoration(this, 0))
        fab.setOnClickListener { view ->
            startActivity(Intent(this, CurrencyActivity::class.java))
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
}
