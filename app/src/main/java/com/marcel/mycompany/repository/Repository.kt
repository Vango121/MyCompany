package com.marcel.mycompany.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcel.mycompany.model.Payroll
import com.marcel.mycompany.model.Worker
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
    fun insertPayroll(payroll: Payroll)
    fun updatePayroll(payroll: Payroll)
    fun deletePayroll(payroll: Payroll)
    fun getAllPayroll() : Deferred<LiveData<List<Payroll>>>
    fun deleteAllPayroll()
    fun getRowCount(): Deferred<LiveData<Int>>
}