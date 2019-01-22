package co.heri.exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import co.heri.exchange.Model.Dao.AppDatabase
import co.heri.exchange.Model.CurrencyList
import co.heri.exchange.Model.CurrencyRecycler
import co.heri.exchange.Model.Dao.CurrencyDao
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_currency.*
import java.io.BufferedReader
import java.io.InputStreamReader
import com.google.gson.GsonBuilder
import kotlin.concurrent.thread




class CurrencyActivity : AppCompatActivity() {

    private lateinit var currencyDao: CurrencyDao;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        setSupportActionBar(search_toolbar)



        if (supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            supportActionBar!!.setDisplayShowHomeEnabled(true);
        }

        val gson = GsonBuilder().create()



            thread {

                /*val database = Room.databaseBuilder(this, AppDatabase::class.java, "app.db")
                        .build();

                currencyDao = database.currencyDao()

                var currencies = currencyDao.getCurrencies();*/



                val br = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.currencies)));
                val textoutput = br.use(BufferedReader::readText)

                val currenciesList: CurrencyList = gson.fromJson(textoutput, CurrencyList::class.java)


                val list_currency_adapter = CurrencyRecycler()


                list_currency_adapter.swapData(currenciesList.currencies!!)

                runOnUiThread {
                    currency_list.layoutManager = LinearLayoutManager(this);
                    currency_list.adapter = list_currency_adapter
                }

            }






        //Log.e("CURRENCIES", currencies.toString())

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.app_bar_search -> {
                Snackbar.make(item.actionView, "Search icon clicked", Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
