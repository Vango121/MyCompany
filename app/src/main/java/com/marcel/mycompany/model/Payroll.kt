package com.marcel.mycompany.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payroll_table")
data class Payroll(@ColumnInfo(name = "workers_list")var workers:List<Worker>, @ColumnInfo(name = "data")var data:String) {
    @PrimaryKey(autoGenerate = true)
    var payroll_id:Int =0
}