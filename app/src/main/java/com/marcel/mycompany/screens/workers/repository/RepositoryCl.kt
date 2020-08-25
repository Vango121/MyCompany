package com.marcel.mycompany.screens.workers.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcel.mycompany.screens.workers.Worker
import kotlinx.coroutines.*

class RepositoryCl (val application: Application): Repository {
    private val shared: SharedPreferences = SharedPreferences()
    private var workersDao: WorkersDao
    init {
        val database = WorkersDatabase.getInstance(application.applicationContext)
        workersDao= database!!.workerDao()
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
}