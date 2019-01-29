package co.heri.exchange.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "rates")
data class Rate(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,

        @field:SerializedName("symbol")
        @ColumnInfo(name = "symbol")
        val symbol: String? = null,

        @field:SerializedName("rate")
        @ColumnInfo(name = "rate")
        val rate: Double? = null,

        @field:SerializedName("time")
        @ColumnInfo(name = "time")
        val currency: Int? = null
)