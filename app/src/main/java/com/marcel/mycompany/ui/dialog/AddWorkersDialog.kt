package com.marcel.mycompany.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcel.mycompany.R
import com.marcel.mycompany.model.Worker
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.dialog_addworker.view.*
import javax.inject.Inject

class AddWorkersDialog @Inject constructor() :  DialogFragment() {
   private val _workerInfo =MutableLiveData<Worker>()
    val workerInfo : LiveData<Worker>
    get() = _workerInfo

    lateinit var worker: Worker
    lateinit var v: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

         v = layoutInflater.inflate(R.layout.dialog_addworker,null)

        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it,R.style.DialogTheme)
            builder
                .setView(v)
                .setPositiveButton("OK"
                ) { dialog, id ->
                    //method overriden below onCLick
                }
                .setNegativeButton(getString(R.string.cancel)
                ) { dialog, id ->
                    // User cancelled the dialog
                }
            // Create the AlertDialog object and return it
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onResume() {
        val dialog: AlertDialog= dialog as AlertDialog
        dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(this::onClick)

        super.onResume()
    }
    fun onClick(view: View){
        val name= v.editTextName?.text.toString()
        val surname= v.editTextSurname?.text.toString()
        val money = v.editTextMoney?.text.toString()
        if(!name.equals("") && !surname.equals("") && !money.equals("")){
            worker= Worker(name,surname,money.toDouble(),0.0,0.0)
            _workerInfo.value=worker
            dismiss()
        }
        else{
            context?.let { Toasty.error(it,getString(R.string.complete_allFields_dialog), Toast.LENGTH_SHORT,true) }?.show()
        }

    }
}