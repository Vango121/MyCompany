package com.marcel.mycompany.screens.workers.dialog.adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcel.mycompany.R
import com.marcel.mycompany.screens.workers.Worker
import kotlinx.android.synthetic.main.payment_row.view.*

class PaymentDialogRVAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workers: List<Worker> = ArrayList()
    private val currentAdvances : MutableList<Double> = ArrayList()
    var workersToUpdate: List<Worker> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val workerview = LayoutInflater.from(parent.context).inflate(
            R.layout.payment_row
            ,parent
            , false)
        return PaymentViewHolder(workerview)
    }

    override fun getItemCount(): Int = workers.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PaymentViewHolder).initUI(workers.get(position))

    }
    fun clearList(){
        for (i in 0..workers.size-1){
            currentAdvances.add(0.0)
        }
    }
    fun getWorkersUpdate(): List<Worker>{
        workersToUpdate=workers
        for (i in 0..workers.size-1){
            workersToUpdate[i].advance=currentAdvances[i]
        }
        return workersToUpdate
    }
    fun withdrawall(): List<Worker>{
        workersToUpdate=workers
        for (i in 0..workers.size-1){
            workersToUpdate[i].hours= 0.0
            workersToUpdate[i].advance=0.0
        }
        return workersToUpdate
    }
    fun addWorkers(list: List<Worker>){
        workers=list
        for (i in 0..list.size-1){
            currentAdvances.add(0.0)
        }
    }
    inner class PaymentViewHolder(var view: View)
        : RecyclerView.ViewHolder(view){
        val data = view.textViewRowWorker
        //val advances:String =Resources.getSystem().getString(R.string.advance)
        val editText= view.editTextAdvance
        fun initUI(worker: Worker){
            val sum = worker.hours*worker.money
            data.setText("${worker.name} ${worker.surName} $sum")
            editText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //empty
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //empty
                }

                override fun afterTextChanged(s: Editable?) {
                    currentAdvances[adapterPosition]=s.toString().toDouble()
                }

            })
        }

    }
}