package com.marcel.mycompany.screens.workers.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcel.mycompany.screens.workers.Worker
import kotlinx.coroutines.Deferred

interface Repository {
    fun getSwitchState(): MutableLiveData<Boolean>
    fun saveSwitchState(boolean: Boolean)
    fun insertWorker(worker: Worker)
    fun updateWorker(worker: Worker)
    fun deleteWorker(worker: Worker)
    fun getAllWorkers() : Deferred<LiveData<List<Worker>>>
    fun deleteAllWorkers()
    fun saveShowCaseState(boolean: Boolean)
    fun getShowCaseState(): MutableLiveData<Boolean>
}