package com.marcel.mycompany.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marcel.mycompany.model.Payroll

@Dao
interface PayrollDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(payroll: Payroll)

    @Update
    fun update(payroll: Payroll)

    @Delete
    fun delete(payroll: Payroll)

    @Query("SELECT * FROM payroll_table")
    fun getAllPayroll() : LiveData<List<Payroll>>

    @Query("DELETE FROM payroll_table")
    fun deleteAllRows()

    @Query("SELECT COUNT(workers_list) FROM payroll_table")
    fun getRowCount(): LiveData<Int>
}