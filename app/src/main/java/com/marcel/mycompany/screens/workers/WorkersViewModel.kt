package com.marcel.mycompany.screens.workers

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcel.mycompany.Event
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking


class WorkersViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigateToDialog = MutableLiveData<Event<String>>()
     val navigateToDialog : LiveData<Event<String>>
        get() = _navigateToDialog

    var checked = MutableLiveData<Boolean>()
    val repository = RepositoryCl(application)
    private var allWorkers: Deferred<LiveData<List<Worker>>> = repository.getAllWorkers()
    init {
    checked=repository.getSwitchState()
    }
    fun saveSwitchState(){
        repository.saveSwitchState( checked.value!!)
    }
    fun userClicksOnButton() { // add worker button click
        _navigateToDialog.value =
            Event("itemId")  // Trigger the event by setting a new Event as a new value
    }
    fun getAllWorkers() : LiveData<List<Worker>> = runBlocking {
        allWorkers.await()
    }
    fun getDataFromDialog(worker:Worker){
        repository.insertWorker(worker)
    }



}