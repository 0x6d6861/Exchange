package co.heri.exchange.Model.Dao

import androidx.room.*
import co.heri.exchange.Model.Currency

@Dao
interface CurrencyDao {

    @Insert
    fun insertAll(currencies: List<Currency>)

    @Update
    fun update(currency: Currency)

    @Delete
    fun delete(currency: Currency)

    @Query("SELECT * FROM currencies")
    fun getCurrencies(): List<Currency>

    @Query("SELECT * FROM currencies WHERE alphabeticCode LIKE :code LIMIT 1")
    fun getCurrencyByCode(code: String): Currency
}