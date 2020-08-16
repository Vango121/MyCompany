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
import kotlinx.android.synthetic.main.dialog_removeworker.view.*

class RemoveWorkerDialog() :  DialogFragment(), DeleteWorkerRVAdapter.OnNoteListener {

    private val _workertoRemove = MutableLiveData<Worker>()
    val workerToRemove : LiveData<Worker>
        get() = _workertoRemove

    var workerList: List<Worker> = ArrayList()
    lateinit var worker:Worker
    var workerAdapter : DeleteWorkerRVAdapter = DeleteWorkerRVAdapter(this)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view: View = layoutInflater.inflate(R.layout.dialog_removeworker,null)
        view.recycler_view_workerlist_remove.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=workerAdapter
        }
        return activity.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(view)
                .setNegativeButton(getString(R.string.cancel)
                ) { dialog, id ->
                    // User cancelled the dialog
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun addData(list: List<Worker>){
    workerAdapter.addWorkers(list)
    workerList=list
        workerAdapter.notifyDataSetChanged()
    }
    override fun onNoteClick(position: Int) {
        _workertoRemove.value=workerList.get(position)

    }

}