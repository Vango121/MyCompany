package com.marcel.mycompany

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class WorkersViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _navigateToDetails = MutableLiveData<Event<String>>()

     val navigateToDetails : LiveData<Event<String>>
        get() = _navigateToDetails

    var checked = MutableLiveData<Boolean>()


    init {
    checked.value=true
    }
    fun userClicksOnButton() {
        _navigateToDetails.value = Event("itemId")  // Trigger the event by setting a new Event as a new value
    }
    fun switch(isChecked: Boolean){
       // _checked.value=isChecked
    }
}