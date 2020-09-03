package com.marcel.mycompany.screens.workers.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.marcel.mycompany.Converters
import com.marcel.mycompany.screens.workers.Payroll
import com.marcel.mycompany.screens.workers.Worker

@Database(entities = arrayOf(Worker::class,Payroll::class), version = 7,exportSchema = true)
@TypeConverters(Converters::class)
abstract class WorkersDatabase : RoomDatabase() {

    abstract fun workerDao() : WorkersDao
    abstract fun payrollDao() : PayrollDao
    companion object{
        private var instance: WorkersDatabase? = null
        fun getInstance(context: Context): WorkersDatabase?{
            if(instance ==null){
                instance = Room.databaseBuilder(
                    context,
                    WorkersDatabase::class.java,
                    "workers_db1")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
        fun deleteInstanceOfDatabase(){
            instance = null
        }
    }
}