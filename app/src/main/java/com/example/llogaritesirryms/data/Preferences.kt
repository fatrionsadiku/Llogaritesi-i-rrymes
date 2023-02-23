package com.example.llogaritesirryms.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object Preferences {
    private const val SHARED_PREFERENCES_KEY = "kalkuluesi_rrymes_prefs"
    private const val A1_E_KALUAR_KEY = "a1 e kaluar"
    private const val A2_E_KALUAR_KEY = "a2 e kaluar"
    private const val NAME_KEY = "name key"
    private const val PASSWORD_KEY = "password key"
    private const val HAS_VALUES_KEY = "Values Key"
    lateinit var prefs : SharedPreferences

    fun init(ctx : Context){
        prefs = ctx.getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE)
    }

    fun saveA1(value : Int){
        prefs.edit().putInt(A1_E_KALUAR_KEY,value).apply()
    }
    fun hasValuesSetState(state : Boolean){
        prefs.edit().putBoolean(HAS_VALUES_KEY,state).apply()
    }
    fun getValuesState() = prefs.getBoolean(HAS_VALUES_KEY,false)

    fun getA1() =
        prefs.getInt(A1_E_KALUAR_KEY,0)

    fun saveA2(value : Int){
        prefs.edit().putInt(A2_E_KALUAR_KEY,value).apply()
    }

    fun getA2() =
        prefs.getInt(A2_E_KALUAR_KEY,0)

    fun saveName(userName : String) = prefs.edit().putString(NAME_KEY,userName).apply()

    fun getName() = prefs.getString(NAME_KEY,"")

    fun savePassword(password : String) = prefs.edit().putString(PASSWORD_KEY,password).apply()
    
    fun getPassword() = prefs.getString(PASSWORD_KEY,"")

}