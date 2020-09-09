package com.marcel.mycompany.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workers_table")
data class Worker(var name: String, var surName: String, var money:Double, var hours:Double, var advance: Double) {
    @PrimaryKey(autoGenerate = true)
    var worker_id:Int =0
}