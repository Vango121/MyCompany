package com.marcel.mycompany.screens.workers

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcel.mycompany.R
import kotlinx.android.synthetic.main.dialog_presence.view.*

class AddButtonDialog :  DialogFragment(){
    var addButtonAdapter : AddButtonRVAdapter = AddButtonRVAdapter()
    var workerList: List<Worker> = ArrayList()
    private var _workertoUpdate =MutableLiveData<List<Worker>>()
    val workertoUpdate : LiveData<List<Worker>>
        get() = _workertoUpdate
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = layoutInflater.inflate(R.layout.dialog_presence,null)
        view.recycler_view_workers_presence.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=addButtonAdapter
        }
        return activity.let {
            var builder =AlertDialog.Builder(it,R.style.DialogTheme)
            builder
                .setView(view)
                .setNegativeButton(getString(R.string.select_all)
                ) { dialog, id ->
                    // User cancelled the dialog
                    _workertoUpdate.value=workerList
                }
                .setPositiveButton("OK"){dialog, id->
                    _workertoUpdate.value=addButtonAdapter.getCheckedWorkers()
                }

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
    fun addData(list: List<Worker>){
        addButtonAdapter.addWorkers(list)
        workerList=list
        addButtonAdapter.notifyDataSetChanged()
    }
}