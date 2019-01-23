package co.heri.exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import co.heri.exchange.Model.CurrencyList
import co.heri.exchange.Model.CurrencyRecycler
import kotlinx.android.synthetic.main.activity_currency.*
import java.io.BufferedReader
import java.io.InputStreamReader
import com.google.gson.GsonBuilder
import kotlin.concurrent.thread
import androidx.recyclerview.widget.DefaultItemAnimator
import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.widget.SearchView
import android.os.Build
import android.view.*



class CurrencyActivity : AppCompatActivity() {


    private lateinit var searchView: SearchView
    private lateinit var list_currency_adapter: CurrencyRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        setSupportActionBar(search_toolbar)



        if (supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)

            whiteNotificationBar(currency_list)

        }

        val gson = GsonBuilder().create()

        currency_list.layoutManager = LinearLayoutManager(this)
        currency_list.itemAnimator = DefaultItemAnimator()

            thread {

                /*val database = Room.databaseBuilder(this, AppDatabase::class.java, "app.db")
                        .build();

                currencyDao = database.currencyDao()

                var currencies = currencyDao.getCurrencies();*/



                val br = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.currencies)))
                val textoutput = br.use(BufferedReader::readText)

                val currenciesList: CurrencyList = gson.fromJson(textoutput, CurrencyList::class.java)
                //Log.e("PARSED_", currenciesList.currencies.toString())

                this.list_currency_adapter = CurrencyRecycler(currenciesList.currencies!!)

                runOnUiThread {
                    progressbarContainer.visibility = View.GONE
                    currency_list.adapter = this.list_currency_adapter
                }

            }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search)
                .actionView as SearchView
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                this@CurrencyActivity.list_currency_adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                this@CurrencyActivity.list_currency_adapter.filter.filter(query)
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.app_bar_search -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified) {
            searchView.isIconified = true
            return
        }
        super.onBackPressed()
    }

    private fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }



}
