package com.example.llogaritesirryms.data.calc

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CalcPackageDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerCalculatedResult(calcPackage: CalcInfo)

    @Query("SELECT * FROM calc_info WHERE username LIKE '%' || :userName || '%'")
    fun getCalculatedResults(userName : String) : Flow<List<CalcInfo>>

    @Query("DELETE FROM calc_info")
    suspend fun deleteAllRecords()

    @Delete
    suspend fun deleteRecord(record : CalcInfo?)
}