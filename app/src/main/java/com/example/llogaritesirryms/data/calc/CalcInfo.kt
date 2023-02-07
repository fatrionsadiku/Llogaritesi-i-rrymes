package com.example.llogaritesirryms.data.calc

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Parcelize
@Entity(tableName = "calc_info")
data class CalcInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "username")
    val userName: String? = "",
    @ColumnInfo(name = "A1_E_SHPENZUAR")
    val totalA1EShpenzuar: String,
    @ColumnInfo(name = "A2_E_SHPENZUAR")
    val totalA2EShpenzuar: String,
    @ColumnInfo(name = "BORGJI_TOTAL")
    val borgjiTotal: String,
    @ColumnInfo(name = "date")
    val created: Long = System.currentTimeMillis(),
) : Parcelable{
    val createdDateFormatted: String
        get() = DateFormat.getTimeInstance().format(created)
}