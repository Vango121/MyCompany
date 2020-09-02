package com.marcel.mycompany.screens.workers.dialog.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcel.mycompany.R
import com.marcel.mycompany.screens.workers.Worker

class EditDialogRVAdapter(val listener: OnNoteListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workers: List<Worker> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val workerview = LayoutInflater.from(parent.context).inflate(
            R.layout.remove_worker_row
            ,parent
            , false)
        return EditViewHolder(workerview,listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EditDialogRVAdapter.EditViewHolder).initializeUiCompontnt(workers.get(position).name +" "+ workers.get(position).surName)
    }

    override fun getItemCount(): Int {
        return workers.size
    }
    fun addWorkers(list: List<Worker>){
        workers=list
    }
    inner class EditViewHolder(var view: View,val listener: OnNoteListener) : RecyclerView.ViewHolder(view),View.OnClickListener{
        var textViewName =view.findViewById<TextView>(R.id.textViewRowWorkerInfo)
        fun initializeUiCompontnt(workerInfo:String){
            textViewName.text=workerInfo
            view.setOnClickListener(this)
        }
        override fun onClick(v:View){
            listener.onClick(layoutPosition)
        }

    }
    interface OnNoteListener{
        fun onClick(position:Int)
    }
}