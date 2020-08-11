package com.marcel.mycompany.screens.workers

import android.content.Context
import androidx.lifecycle.MutableLiveData

class RepositoryCl:Repository {
    val shared:SharedPreferences = SharedPreferences()

    override fun getSwitchState(context: Context): MutableLiveData<Boolean> {
        return shared.getSwitchState(context)
    }

    override fun saveSwitchState(context: Context,boolean: Boolean) {
        shared.saveSwithch(context,boolean)
    }
}