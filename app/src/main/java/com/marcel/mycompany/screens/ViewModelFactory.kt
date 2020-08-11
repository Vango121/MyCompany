package com.marcel.mycompany.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcel.mycompany.screens.workers.AddWorkersDialog
import com.marcel.mycompany.screens.workers.WorkersViewModel

class ViewModelFactory(val application: Application,val dialog: AddWorkersDialog): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WorkersViewModel::class.java)){
            return WorkersViewModel(application,dialog) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}