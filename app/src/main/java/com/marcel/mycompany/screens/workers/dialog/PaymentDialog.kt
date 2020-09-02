package com.marcel.mycompany.screens.workers.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcel.mycompany.R
import com.marcel.mycompany.screens.workers.Worker
import com.marcel.mycompany.screens.workers.dialog.adapters.PaymentDialogRVAdapter
import kotlinx.android.synthetic.main.dialog_payment.view.*

class PaymentDialog :DialogFragment() {

    lateinit var v: View
    var paymentAdapter = PaymentDialogRVAdapter()
    lateinit var a:AlertDialog
    private var _workertoUpdate = MutableLiveData<List<Worker>>()
    val workertoUpdate : LiveData<List<Worker>>
        get() = _workertoUpdate
    var withdrawAll = MutableLiveData<Boolean>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        v= layoutInflater.inflate(R.layout.dialog_payment, null)
        v.recycler_view_workers_payment.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=paymentAdapter
        }
       return activity.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialogTheme)
            builder.setView(v)
                .setPositiveButton("Ok"){ dialog, id ->
                    _workertoUpdate.value=paymentAdapter.getWorkersUpdate()
                    paymentAdapter.clearList()
                }
                .setNegativeButton(getString(R.string.withdraw_all)){ dialog, id ->
                    withdrawAll.value=true
                    _workertoUpdate.value=paymentAdapter.withdrawall()
                    paymentAdapter.clearList()

                }
                a= builder.create()
           a
        }
    }

    override fun onStart() {
        super.onStart()
        val window: Window? = a.window
        if (window != null) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        }
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        }
    }
    fun addData(list: List<Worker>){
        paymentAdapter.addWorkers(list)
        //workerList=list
        paymentAdapter.notifyDataSetChanged()
    }
    fun clearListInAdapter(){
        paymentAdapter.clearList()
    }
}