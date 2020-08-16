package com.marcel.mycompany.screens.workers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workers_table")
data class Worker(val name: String, val surName: String,val money:Double, var hours:Double=0.0) {
    @PrimaryKey(autoGenerate = true)
    var worker_id:Int =0
}