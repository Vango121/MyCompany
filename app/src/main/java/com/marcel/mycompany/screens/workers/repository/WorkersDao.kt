package com.marcel.mycompany.screens.workers.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marcel.mycompany.screens.workers.Worker

@Dao
interface WorkersDao {

    @Insert
    fun insert(worker: Worker)

    @Update
    fun update(worker: Worker)

    @Delete
    fun delete(worker: Worker)

    @Query("SELECT * FROM workers_table")
    fun getAllWorkers() : LiveData<List<Worker>>

    @Query("DELETE FROM workers_table")
    fun deleteAllRows()

}