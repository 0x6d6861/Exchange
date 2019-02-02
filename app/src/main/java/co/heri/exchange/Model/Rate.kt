package co.heri.exchange.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "rates")
data class Rate(

        @PrimaryKey()
        @field:SerializedName("symbol")
        @ColumnInfo(name = "symbol")
        val symbol: String,

        @field:SerializedName("rate")
        @ColumnInfo(name = "rate")
        val rate: Double? = null,

        @field:SerializedName("time")
        @ColumnInfo(name = "time")
        val time: Long? = null
)