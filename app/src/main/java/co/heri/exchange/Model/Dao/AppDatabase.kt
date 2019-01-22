package co.heri.exchange.Model.Dao

import androidx.room.Database
import androidx.room.RoomDatabase
import co.heri.exchange.Model.Currency

@Database(entities = arrayOf(Currency::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}