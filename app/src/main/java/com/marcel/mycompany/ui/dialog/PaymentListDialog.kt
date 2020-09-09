package com.marcel.mycompany.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.marcel.mycompany.R
import com.marcel.mycompany.model.Payroll
import com.marcel.mycompany.ui.dialog.adapters.PaymentListRVAdapter
import kotlinx.android.synthetic.main.dialog_payment_list_dialog.view.*
import javax.inject.Inject

class PaymentListDialog @Inject constructor():  DialogFragment(), PaymentListRVAdapter.OnNoteListener {
    private lateinit var v: View
    private var payrollAdapter  = PaymentListRVAdapter(this)
    private var payrollList: List<Payroll> = ArrayList()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        v = layoutInflater.inflate(R.layout.dialog_payment_list_dialog,null)
        v.recycler_view_daylist.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=payrollAdapter
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
    fun addData(list: List<Payroll>){
        payrollAdapter.addPayrolls(list)
        payrollList=list
        payrollAdapter.notifyDataSetChanged()
    }

    override fun onClick(position: Int) {
        val payrollDialog = PayrollDialog()
        payrollDialog.addData(payrollList[position].workers)
        payrollDialog.show(parentFragmentManager,"dialoghistory")
    }
}