package com.marcel.mycompany.screens.workers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcel.mycompany.R
import kotlinx.android.synthetic.main.presence_workers_row.view.*

class AddButtonRVAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workers: List<Worker> = ArrayList()
    private var workersChecked: MutableList<Worker> = ArrayList()
    var workersToUpdate: List<Worker> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val workerview = LayoutInflater.from(parent.context).inflate(
            R.layout.presence_workers_row
            ,parent
            , false)
        return AddButtonViewHoler(workerview)
    }

    override fun getItemCount(): Int {
        return workers.size
    }
    fun addWorkers(list: List<Worker>){
        workers=list
    }
    fun getCheckedWorkers(): List<Worker>{
        workersToUpdate=workersChecked
        return workersToUpdate
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AddButtonRVAdapter.AddButtonViewHoler).initializeUiCompontnt(workers?.get(position)?.name+" "+ workers?.get(position)?.surName)
    }
    inner class AddButtonViewHoler(var view: View) : RecyclerView.ViewHolder(view),View.OnClickListener{
        var textViewName = view.textViewRowWorkerName
        var checkBox = view.checkBoxWorkerPresence

        fun initializeUiCompontnt(workerName : String){
            textViewName.setText(workerName)
            checkBox.setOnClickListener(this::onClick)
        }
        override fun onClick(v: View?) { //checkbox onClick
            val position :Int = workersChecked.indexOf(workers[adapterPosition])
            Log.i("position",position.toString())
            when(position){
                -1 -> workersChecked.add(workers[adapterPosition])
                else -> workersChecked.removeAt(position)
            }


        }

    }
}