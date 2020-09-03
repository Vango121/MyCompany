package com.marcel.mycompany.screens.workers

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.marcel.mycompany.Event
import com.marcel.mycompany.screens.workers.repository.RepositoryCl
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


class WorkersViewModel(application: Application) : AndroidViewModel(application) {

    private val _navigateToDialog = MutableLiveData<Event<String>>()
     val navigateToDialog : LiveData<Event<String>>
        get() = _navigateToDialog

    private val _navigateToRemoveDialog = MutableLiveData<Event<String>>()
    val navigateToRemoveDialog : LiveData<Event<String>>
        get() = _navigateToRemoveDialog
    private val _navigateToEditDialog = MutableLiveData<Event<String>>()
    val navigateToEditDialog : LiveData<Event<String>>
        get() = _navigateToEditDialog

    private val _addButton = MutableLiveData<Event<String>>()
    val addButton : LiveData<Event<String>>
        get() = _addButton
    private val _payroll = MutableLiveData<Event<String>>()
    val payroll : LiveData<Event<String>>
        get() = _payroll

    private val _payment = MutableLiveData<Event<String>>()
    val payment : LiveData<Event<String>>
        get() = _payment

    private val _paymentListDialog = MutableLiveData<Event<String>>()
    val paymentListDialog  : LiveData<Event<String>>
        get() = _paymentListDialog
    var application1 = application
    var checked = MutableLiveData<Boolean>()
    var showCaseState = MutableLiveData<Boolean>()
    val repository :RepositoryCl by lazy {
        RepositoryCl(application)
    }
    private var allWorkers: Deferred<LiveData<List<Worker>>> = repository.getAllWorkers()
    private var allPayrolls : Deferred<LiveData<List<Payroll>>> = repository.getAllPayroll()
    init {
        checked=repository.getSwitchState()
        showCaseState= repository.getShowCaseState()
    }
    fun saveSwitchState(){
        repository.saveSwitchState( checked.value!!)
    }
    fun saveShowCaseState(){ // save if executed only true
        repository.saveShowCaseState(true)
        showCaseState.value=true
    }
    fun userClicksOnButton() { // add worker button click
        _navigateToDialog.value =
            Event("addWorker")  // Trigger the event by setting a new Event as a new value
    }
    fun userClicksOnRemoveButton() { // removeworker button click
        _navigateToRemoveDialog.value =
            Event("removeWorker")  // Trigger the event by setting a new Event as a new value
    }
    fun userClicksOnEditButton() { // Editworker button click
        _navigateToEditDialog.value =
            Event("editWorker")  // Trigger the event by setting a new Event as a new value
    }

    fun addButton(){ // add button ui method
        _addButton.value=Event("AddButton")
    }
    fun payroll(){
        _payroll.value=Event("Payroll")
    }
    fun payment(){
        _payment.value=Event("Payment")
    }
    fun paymentListDialog() { // dialog with previous payrolls
        _paymentListDialog.value= Event("paymentListDialog")
    }
    fun getAllPayrolls() : LiveData<List<Payroll>> = runBlocking {
        allPayrolls.await()
    }
    fun getAllWorkers() : LiveData<List<Worker>> = runBlocking {
        allWorkers.await()
    }
    fun getDataFromDialog(worker:Worker){
        repository.insertWorker(worker)
    }
    fun deleteWorker(worker:Worker){
        repository.deleteWorker(worker)
    }
    fun updateWorker(worker: Worker){
        repository.updateWorker(worker)
    }
    fun insertPayroll(payroll: Payroll){
        repository.insertPayroll(payroll)
    }
    fun updatePayroll(payroll: Payroll){
        repository.updatePayroll(payroll)
    }
    fun calcHours(start:String, end: String): Double{
        val format = SimpleDateFormat("HH:mm",Locale.getDefault())
        val date1: Date = format.parse(start)!!
        val date2: Date = format.parse(end)!!
        val difference = date2.time - date1.time

        val diffInHrs = ((difference / (1000*60*60)) % 24)
        val diffInMin=((difference / (1000*60)) % 60) - diffInHrs*60
        val hrsWorked: Double
        if(diffInMin in 31..49){
            hrsWorked=diffInHrs+0.5
        }else if (diffInMin>=50){
            hrsWorked= (diffInHrs+1).toDouble()
        }
        else{
            hrsWorked=diffInHrs.toDouble()
        }
        application1.applicationContext?.let { Toasty.info(it,"Hours worked $hrsWorked", Toast.LENGTH_SHORT,true).show() }
        return hrsWorked
    }
    fun getCurrentDate():String{
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        return df.format(c)
    }
    fun calcTotalAmount(list:List<Worker>):Double{ // calc earned money by workers to display it on worker screen
        var totalAmount =0.0
        for (person: Worker in list) {
            totalAmount += (person.hours * person.money) - person.advance
        }
        return totalAmount
    }
    fun getRowCount(): LiveData<Int> = runBlocking {
        repository.getRowCount().await()
    }
    fun withdraw() { // update workers money
        val workersList = getAllWorkers().value!!
        for (i in 0..workersList.size - 1) {
            workersList[i].hours = 0.0
            workersList[i].advance = 0.0
            updateWorker(workersList[i])
        }
    }
    fun withdrawAll() { // put into payroll new record
        val workers = getAllWorkers().value!!
        insertPayroll(Payroll(workers,getCurrentDate()))
        Log.i("ss",Gson().toJson(workers))
//        GlobalScope.launch {
//            delay(5000)
//            for (i in 0..workers.size-1){
//                workers[i].hours= 0.0
//                workers[i].advance=0.0
//                updateWorker(workers[i])
//            }
//        }

    }

}