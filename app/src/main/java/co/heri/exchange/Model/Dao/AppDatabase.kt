package co.heri.exchange.Model.Dao

import androidx.room.Database
import androidx.room.RoomDatabase
import co.heri.exchange.Model.Currency
import co.heri.exchange.Model.Rate

@Database(entities = [Currency::class, Rate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun rateDao(): RateDao
}