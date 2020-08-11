package com.marcel.mycompany.screens.workers

import android.content.Context
import androidx.lifecycle.MutableLiveData

interface Repository {
    fun getSwitchState(context: Context): MutableLiveData<Boolean>
    fun saveSwitchState(context: Context,boolean: Boolean)
}