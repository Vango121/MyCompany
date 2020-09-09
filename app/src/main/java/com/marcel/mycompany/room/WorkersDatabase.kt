package com.marcel.mycompany.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marcel.mycompany.model.Payroll
import com.marcel.mycompany.model.Worker

@Database(entities = arrayOf(Worker::class, Payroll::class), version = 7,exportSchema = true)
@TypeConverters(Converters::class)
abstract class WorkersDatabase : RoomDatabase() {

    abstract fun workerDao() : WorkersDao
    abstract fun payrollDao() : PayrollDao

}