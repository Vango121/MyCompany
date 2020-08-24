package com.marcel.mycompany.screens.workers

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcel.mycompany.R
import kotlinx.android.synthetic.main.dialog_payroll.view.*
import kotlinx.android.synthetic.main.dialog_removeworker.view.*

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