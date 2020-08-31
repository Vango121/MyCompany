package com.marcel.mycompany.screens.workers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payroll_table")
data class Payroll(val workers:ArrayList<Worker>,val data:String) {
    @PrimaryKey(autoGenerate = true)
    var payroll_id:Int =0
}