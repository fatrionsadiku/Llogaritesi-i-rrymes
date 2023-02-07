package com.example.llogaritesirryms.data.calc

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Parcelize
data class CalcPackage(
    val a1ekaluar: Int,
    val a1etashme: Int,
    val a2ekaluar: Int,
    val a2etashme: Int,
    val userName: String? = "",
) : Parcelable