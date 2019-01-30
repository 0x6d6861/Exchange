package co.heri.exchange

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.heri.exchange.Model.Country
import co.heri.exchange.Model.CountryRecyclerAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.room.Room
import co.heri.exchange.Model.Currency
import co.heri.exchange.Model.Dao.AppDatabase
import co.heri.exchange.Model.Dao.DBHelper
import kotlin.concurrent.thread
import co.heri.exchange.R


class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var savedCurrencies: List<Currency>
    private lateinit var list_country_adapter: CountryRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


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

            this.savedCurrencies = this.database.currencyDao().getCurrencies();

            val currencies: MutableList<Country> = mutableListOf<Country>()

            for(currency in savedCurrencies){
                currencies.add(Country(name = currency.entity ?: "Not set", currency = currency.alphabeticCode, amount = "658.45", flag = currency.flagpng ?: "No flag"))
            }


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

        builder.setView(layoutInflater.inflate(R.layout.currency_select, null))


        builder.setTitle("Select Currency")
                /*.setPositiveButton("Okay"
                ) { dialog, id ->
                    //mListener.onDialogPositiveClick(this)

                }
                .setNegativeButton("Cancel"
                ) { dialog, id ->
                    // mListener.onDialogNegativeClick(this)
                    dialog.cancel()
                }*/

        builder.create()
        builder.show()

    }
}
