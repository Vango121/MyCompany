package com.marcel.mycompany.screens.workers

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcel.mycompany.Event
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer

class WorkersViewModel(application: Application, dialog: AddWorkersDialog) : AndroidViewModel(application) {

    private val _navigateToDetails = MutableLiveData<Event<String>>()

     val navigateToDetails : LiveData<Event<String>>
        get() = _navigateToDetails

    var checked = MutableLiveData<Boolean>()
    val sharedpref = RepositoryCl()
    val workersdialog = dialog

    init {
    checked=sharedpref.getSwitchState(getApplication())
    workersdialog.getChanges().subscribe{
        Log.i("imie",it.name)
    }
    }
    fun saveSwitchState(){
        sharedpref.saveSwitchState(getApplication(), checked.value!!)
    }
    fun userClicksOnButton() { // add worker button click
        _navigateToDetails.value =
            Event("itemId")  // Trigger the event by setting a new Event as a new value
    }


}