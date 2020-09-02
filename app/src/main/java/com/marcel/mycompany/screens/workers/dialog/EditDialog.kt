package com.marcel.mycompany.screens.workers.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcel.mycompany.R
import com.marcel.mycompany.screens.workers.Worker
import com.marcel.mycompany.screens.workers.dialog.adapters.DeleteWorkerRVAdapter
import com.marcel.mycompany.screens.workers.dialog.adapters.EditDialogRVAdapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.dialog_edit.view.*
import kotlinx.android.synthetic.main.dialog_removeworker.view.*
import kotlinx.android.synthetic.main.dialog_removeworker.view.recycler_view_workerlist_remove
import kotlinx.android.synthetic.main.dialog_secondedit.view.*

class EditDialog :  DialogFragment(),EditDialogRVAdapter.OnNoteListener{
    private lateinit var v: View
    private var workerList: List<Worker> = ArrayList()
    private var workerAdapter  = EditDialogRVAdapter(this)
    private lateinit var viewEdit:View
    private val _workerToEdit = MutableLiveData<Worker>()
    val workerToEdit : LiveData<Worker>
        get() = _workerToEdit
    private lateinit var worker:Worker
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        v = layoutInflater.inflate(R.layout.dialog_edit,null)

        v.recycler_view_workers_edit.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=workerAdapter
        }
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.AlertDialogTheme)
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

    override fun onClick(position: Int) {
        //context?.let { Toasty.info(it,workerList[position].name).show() }
        worker =workerList[position]
        viewEdit = layoutInflater.inflate(R.layout.dialog_secondedit,null)
        viewEdit.editTextName.setText(workerList[position].name)
        viewEdit.editTextSurname.setText(workerList[position].surName)
        viewEdit.editTextMoney.setText(workerList[position].money.toString())
        AlertDialog.Builder(context)
            .setPositiveButton("Ok") { dialog, id ->
                worker.name=viewEdit.editTextName.text.toString()
                worker.surName=viewEdit.editTextSurname.text.toString()
                worker.money=viewEdit.editTextMoney.text.toString().toDouble()
                _workerToEdit.value=worker
            }
            .setView(viewEdit)
            .create()
            .show()
    }
}