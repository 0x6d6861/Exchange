package co.heri.exchange.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currencies")
data class Currency(
		@PrimaryKey(autoGenerate = true)
	var id: Int = 0,


		@field:SerializedName("Entity")
	@ColumnInfo(name = "entity")
	val entity: String? = null,

		@field:SerializedName("flag")
        @ColumnInfo(name = "flag")
	val flag: String? = null,

		@field:SerializedName("flagpng")
        @ColumnInfo(name = "flagpng")
	val flagpng: String? = null,

		@field:SerializedName("NumericCode")
        @ColumnInfo(name = "numericCode")
	val numericCode: Double? = null,

		@field:SerializedName("Currency")
        @ColumnInfo(name = "currency")
	val currency: String? = null,

		@field:SerializedName("AlphabeticCode")
        @ColumnInfo(name = "alphabeticCode")
	val alphabeticCode: String? = null,

		@field:SerializedName("WithdrawalDate")
        @ColumnInfo(name = "withdrawalDate")
	val withdrawalDate: String? = null,

		@field:SerializedName("MinorUnit")
        @ColumnInfo(name = "minorUnit")
	val minorUnit: String? = null,

		var selected: Boolean = false
)