package co.heri.exchange.Model.Dao

import androidx.room.*
import co.heri.exchange.Model.Rate

@Dao
interface RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rates: List<Rate>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rate: Rate)

    @Update
    fun update(rate: Rate)

    @Delete
    fun delete(rate: Rate)

    @Query("SELECT * FROM rates")
    fun getrates(): List<Rate>

    @Query("SELECT * FROM rates WHERE symbol LIKE :symbol LIMIT 1")
    fun getRateBySymbol(symbol: String): Rate
}