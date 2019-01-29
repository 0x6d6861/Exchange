package co.heri.exchange.Model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currencies")
data class Currency(



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

        @PrimaryKey()
		@NonNull
		@field:SerializedName("AlphabeticCode")
        @ColumnInfo(name = "alphabeticCode")
	val alphabeticCode: String,

		@field:SerializedName("WithdrawalDate")
        @ColumnInfo(name = "withdrawalDate")
	val withdrawalDate: String?,

		@field:SerializedName("MinorUnit")
        @ColumnInfo(name = "minorUnit")
	val minorUnit: String? = null,

		var selected: Boolean = false
)