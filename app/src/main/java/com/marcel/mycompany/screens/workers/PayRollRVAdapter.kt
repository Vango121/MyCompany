package com.marcel.mycompany.screens.workers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcel.mycompany.R
import kotlinx.android.synthetic.main.payroll_row.view.*

class PayRollRVAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workers: List<Worker> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val workerview = LayoutInflater.from(parent.context).inflate(
            R.layout.payroll_row
            ,parent
            , false)
        return WorkerViewHolder(workerview)
    }

    override fun getItemCount(): Int {
        return workers.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WorkerViewHolder).initUi(workers.get(position))
    }
    fun addWorkers(list: List<Worker>){
        workers=list
    }
    inner class WorkerViewHolder(var view: View)
        : RecyclerView.ViewHolder(view){
        val firstName = view.textViewRowName
        val surname = view.textViewRowSurname
        val earnings = view.textViewRowEarnings
        val advance = view.textViewRowAdvance
        val sum = view.textViewRowSum
        fun initUi(worker:Worker){
            firstName.setText(worker.name)
            surname.setText(worker.surName)
            val money : Double = worker.money*worker.hours
            earnings.setText(money.toString())
            advance.setText(worker.advance.toString())
            sum.setText((money-worker.advance).toString())
        }
    }

}