package co.heri.exchange.Model.Dao

import androidx.room.*
import co.heri.exchange.Model.Currency
import androidx.room.OnConflictStrategy



@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: List<Currency>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currency: Currency)

    @Update
    fun update(currency: Currency)

    @Delete
    fun delete(currency: Currency)

    @Query("SELECT * FROM currencies")
    fun getCurrencies(): List<Currency>

    @Query("SELECT * FROM currencies WHERE alphabeticCode LIKE :code LIMIT 1")
    fun getCurrencyByCode(code: String): Currency
}