package com.marcel.mycompany.screens.workers.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marcel.mycompany.screens.workers.Payroll

@Dao
interface PayrollDao {
    @Insert
    fun insert(payroll: Payroll)

    @Update
    fun update(payroll: Payroll)

    @Delete
    fun delete(payroll: Payroll)

    @Query("SELECT * FROM payroll_table")
    fun getAllPayroll() : LiveData<List<Payroll>>

    @Query("DELETE FROM payroll_table")
    fun deleteAllRows()

}