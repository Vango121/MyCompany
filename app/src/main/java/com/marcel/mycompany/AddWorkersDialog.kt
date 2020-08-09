package com.marcel.mycompany

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

class AddWorkersDialog :  DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = layoutInflater.inflate(R.layout.dialog_addworker,null)
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder
                .setView(view)
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialog, id ->
                        // FIRE ZE MISSILES!
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