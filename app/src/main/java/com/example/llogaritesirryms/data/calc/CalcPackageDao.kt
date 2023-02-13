package com.example.llogaritesirryms.data.calc

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CalcPackageDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerCalculatedResult(calcPackage: CalcInfo)

    @Query("SELECT * FROM calc_info")
    fun getCalculatedResults() : Flow<List<CalcInfo>>
}