package com.marcel.mycompany.screens.workers.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcel.mycompany.R
import com.marcel.mycompany.screens.workers.Worker
import kotlinx.android.synthetic.main.dialog_payroll.view.*

class PayrollDialog :  DialogFragment() {
    lateinit var v: View
    var workerAdapter : PayRollRVAdapter = PayRollRVAdapter()
    var workerList: List<Worker> = ArrayList()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        v = layoutInflater.inflate(R.layout.dialog_payroll,null)
        v.recycler_view_workers_payroll.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=workerAdapter
        }
        return activity.let {
            val builder =AlertDialog.Builder(it,R.style.AlertDialogTheme)
            builder
                .setView(v)
                .setNegativeButton(getString(R.string.close)
                ) { dialog, id ->
                    // User cancelled the dialog
                }
            // Create the AlertDialog object and return it
            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
    fun addData(list: List<Worker>){
        workerAdapter.addWorkers(list)
        workerList=list
        workerAdapter.notifyDataSetChanged()
    }
}