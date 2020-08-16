package com.marcel.mycompany.screens.workers

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.marcel.mycompany.R

class AddButtonDialog :  DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = layoutInflater.inflate(R.layout.dialog_presence,null)

        return activity.let {
            var builder =AlertDialog.Builder(it)
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

}