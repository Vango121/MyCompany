package com.marcel.mycompany.screens.workers.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcel.mycompany.R
import com.marcel.mycompany.screens.workers.Worker

class DeleteWorkerRVAdapter(val onNoteListener: OnNoteListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workers: List<Worker> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val workerview = LayoutInflater.from(parent.context).inflate(
            R.layout.remove_worker_row
            ,parent
            , false)
        return WorkerViewHolder(workerview,onNoteListener)
    }

    override fun getItemCount(): Int {
        return workers.size
    }
    fun addWorkers(list: List<Worker>){
        workers=list
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WorkerViewHolder).initializeUiCompontnt(workers?.get(position)?.name+" "+ workers?.get(position)?.surName)

    }
    inner class WorkerViewHolder(var view: View, val onNoteListener: OnNoteListener)
        : RecyclerView.ViewHolder(view)
    ,View.OnClickListener{
        var textViewName =view.findViewById<TextView>(R.id.textViewRowWorkerInfo)

        fun initializeUiCompontnt(workerInfo:String){
            textViewName.text=workerInfo
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onNoteListener.onNoteClick(adapterPosition)
        }


    }
    interface OnNoteListener{
        fun onNoteClick(position:Int)
    }
}