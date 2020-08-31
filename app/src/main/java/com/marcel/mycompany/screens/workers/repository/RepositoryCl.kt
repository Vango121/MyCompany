package com.marcel.mycompany.screens.workers.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcel.mycompany.screens.workers.Payroll
import com.marcel.mycompany.screens.workers.Worker
import kotlinx.coroutines.*

class RepositoryCl (val application: Application): Repository {
    private val shared: SharedPreferences = SharedPreferences()
    private var workersDao: WorkersDao
    private var payrollDao: PayrollDao
    init {
        val database = WorkersDatabase.getInstance(application.applicationContext)
        workersDao= database!!.workerDao()
        payrollDao= database.payrollDao()
    }
    override fun getSwitchState(): MutableLiveData<Boolean> {
        return shared.getSwitchState(application.applicationContext)
    }

    override fun saveSwitchState(boolean: Boolean) {
        shared.saveSwithch(application.applicationContext,boolean)
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
        shared.saveShowCaseState(application.applicationContext,boolean)
    }

    override fun getShowCaseState(): MutableLiveData<Boolean> = shared.getShowCaseState(application.applicationContext)

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

}