package com.marcel.mycompany.screens.workers

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcel.mycompany.R
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.dialog_addworker.view.*

class AddWorkersDialog :  DialogFragment() {
   //private val _workerInfo =MutableLiveData<Worker>()
    //val workerInfo : LiveData<Worker>
    //get() = _workerInfo
    lateinit var worker:Worker
    var workerInfo :PublishSubject<Worker> = PublishSubject.create()
    fun getChanges():Observable<Worker>{
        return workerInfo
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view: View = layoutInflater.inflate(R.layout.dialog_addworker,null)

        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder
                .setView(view)
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        val name=view.editTextName.text.toString()
                        val surname=view.editTextSurname.text.toString()
                        val money = view.editTextMoney.text.toString()
                        if(!name.equals("") && !surname.equals("") && !money.equals("")){
                            worker=Worker(name,surname,money.toFloat())
                            workerInfo.onNext(worker)
                        }


                        //workerInfo.onComplete()
                    })
                .setNegativeButton(getString(R.string.cancel),
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")

    }
}