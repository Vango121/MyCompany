package com.marcel.mycompany.screens.workers.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.marcel.mycompany.R

class PaymentDialog :DialogFragment() {

    lateinit var v: View
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        v= layoutInflater.inflate(R.layout.dialog_payment,null)
       return activity.let {
            val builder = AlertDialog.Builder(it,R.style.AlertDialogTheme)
            builder.setView(v)
                .setPositiveButton("Ok"){dialog, id ->
                }
                .setNegativeButton(getString(R.string.withdraw_all)){dialog, id ->
                }
                builder.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}