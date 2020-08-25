package com.marcel.mycompany.screens.workers.dialog

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcel.mycompany.screens.workers.Worker

class PaymentDialogRVAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var workers: List<Worker> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = workers.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}