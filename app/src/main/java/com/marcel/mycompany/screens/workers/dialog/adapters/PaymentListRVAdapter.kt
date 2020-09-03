package com.marcel.mycompany.screens.workers.dialog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcel.mycompany.R
import com.marcel.mycompany.screens.workers.Payroll
import com.marcel.mycompany.screens.workers.Worker


class PaymentListRVAdapter(val listener: OnNoteListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var payrollList: List<Payroll> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val paymentview = LayoutInflater.from(parent.context).inflate(
            R.layout.remove_worker_row
            ,parent
            , false)
        return PaymentListViewHolder(paymentview,listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PaymentListRVAdapter.PaymentListViewHolder).initializeUiCompontnt(payrollList[position].data)
    }

    override fun getItemCount(): Int {
       return payrollList.size
    }
    fun addPayrolls(list: List<Payroll>){
        payrollList=list
    }
    inner class PaymentListViewHolder(var view: View, val listener: OnNoteListener) : RecyclerView.ViewHolder(view),
        View.OnClickListener{
        var textViewName =view.findViewById<TextView>(R.id.textViewRowWorkerInfo)
        fun initializeUiCompontnt(data:String){
            textViewName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            textViewName.text=data
            view.setOnClickListener(this)
        }
        override fun onClick(v: View){
            listener.onClick(layoutPosition)
        }

    }
    interface OnNoteListener{
        fun onClick(position:Int)
    }
}