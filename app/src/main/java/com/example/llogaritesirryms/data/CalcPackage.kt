package com.example.llogaritesirryms.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_info")
data class CalcPackage(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "A1_E_KALUAR")
    val a1ekaluar : Int,
   @ColumnInfo(name = "A1_E_TASHME")
    val a1etashme : Int,
   @ColumnInfo(name = "A2_E_KALUAR")
    val a2ekaluar : Int,
   @ColumnInfo(name = "A2_E_TASHME")
    val a2etashme : Int,
    val userName : String? = ""
) : Parcelable