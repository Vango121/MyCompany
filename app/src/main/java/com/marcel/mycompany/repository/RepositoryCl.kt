package com.marcel.mycompany.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.marcel.mycompany.room.PayrollDao
import com.marcel.mycompany.model.Payroll
import com.marcel.mycompany.model.Worker
import com.marcel.mycompany.room.WorkersDao
import com.marcel.mycompany.room.WorkersDatabase
import kotlinx.coroutines.*

class RepositoryCl (private val context: Context, private val workersDao: WorkersDao, private val payrollDao: PayrollDao, private val shared: SharedPreferences): Repository {
    override fun getSwitchState(): MutableLiveData<Boolean> {
        return shared.getSwitchState(context)
    }

    override fun saveSwitchState(boolean: Boolean) {
        shared.saveSwithch(context,boolean)
    }

    override fun insertWorker(worker: Worker) {
        CoroutineScope(Dispatchers.IO).launch {
            workersDao.insert(worker)
        }
    }

    override fun updateWorker(worker: Worker) {
        CoroutineScope(Dispatchers.IO).launch {
            workersDao.update(worker)
        }
    }

    override fun deleteWorker(worker: Worker) {
        CoroutineScope(Dispatchers.IO).launch {
            workersDao.delete(worker)
        }
    }

    override fun getAllWorkers(): Deferred<LiveData<List<Worker>>> =
        CoroutineScope(Dispatchers.IO).async {
            workersDao.getAllWorkers()
        }


    override fun deleteAllWorkers() {
        CoroutineScope(Dispatchers.IO).launch {
            workersDao.deleteAllRows()
        }
    }

    override fun saveShowCaseState(boolean: Boolean) {
        shared.saveShowCaseState(context,boolean)
    }

    override fun getShowCaseState(): MutableLiveData<Boolean> = shared.getShowCaseState(context)

    override fun insertPayroll(payroll: Payroll) {
        CoroutineScope(Dispatchers.IO).launch {
            payrollDao.insert(payroll)
        }
    }

    override fun updatePayroll(payroll: Payroll) {
        CoroutineScope(Dispatchers.IO).launch {
            payrollDao.update(payroll)
        }
    }

    override fun deletePayroll(payroll: Payroll) {
        CoroutineScope(Dispatchers.IO).launch {
            payrollDao.delete(payroll)
        }
    }

    override fun getAllPayroll(): Deferred<LiveData<List<Payroll>>> =
        CoroutineScope(Dispatchers.IO).async {
            payrollDao.getAllPayroll()
        }

    override fun deleteAllPayroll() {
        CoroutineScope(Dispatchers.IO).launch {
            payrollDao.deleteAllRows()
        }
    }

    override fun getRowCount(): Deferred<LiveData<Int>> =
        CoroutineScope(Dispatchers.IO).async {
            payrollDao.getRowCount()
        }


}