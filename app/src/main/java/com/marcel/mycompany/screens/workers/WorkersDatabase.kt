package com.marcel.mycompany.screens.workers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Worker::class], version = 3,exportSchema = true)
abstract class WorkersDatabase : RoomDatabase() {

    abstract fun workerDao() : WorkersDao

    companion object{
        private var instance: WorkersDatabase? = null
        fun getInstance(context: Context):WorkersDatabase?{
            if(instance==null){
                instance= Room.databaseBuilder(
                    context,
                    WorkersDatabase::class.java,
                    "workers_table")

                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
        fun deleteInstanceOfDatabase(){
            instance= null
        }
    }
}