package com.marcel.mycompany.screens.workers

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.marcel.mycompany.MainActivity

class SharedPreferences {
    val DEFAULT_SWITCH_STATE= true
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
}