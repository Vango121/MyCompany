package com.marcel.mycompany.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import javax.inject.Inject

class SharedPreferences @Inject constructor() {
    val DEFAULT_SWITCH_STATE= true
    val DEFAULT_SHOW_CASE_STATE = false // false - didn't display yet true - displayed
     fun getSwitchState(context: Context): MutableLiveData<Boolean> {
        var data=MutableLiveData<Boolean>()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        var value=sharedPreferences.getBoolean("switch",DEFAULT_SWITCH_STATE)
        data.value= value
        return data
    }
    fun saveSwithch(context: Context, boolean: Boolean){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        sharedPreferences.edit().putBoolean("switch",boolean).apply()

    }
    fun saveShowCaseState(context: Context, boolean: Boolean){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        sharedPreferences.edit().putBoolean("workersShowCaseState",boolean).apply()

    }
    fun getShowCaseState(context: Context): MutableLiveData<Boolean>{
        val data=MutableLiveData<Boolean>()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        val value=sharedPreferences.getBoolean("workersShowCaseState",DEFAULT_SHOW_CASE_STATE)
        data.value= value
        return data
    }
}