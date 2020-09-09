package com.marcel.mycompany.di

import android.content.Context
import androidx.room.Room
import com.marcel.mycompany.room.PayrollDao
import com.marcel.mycompany.room.WorkersDao
import com.marcel.mycompany.room.WorkersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): WorkersDatabase {
        return Room
            .databaseBuilder(
                context,
                WorkersDatabase::class.java,
                "workers_db1")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWorkersDAO(workersDatabase: WorkersDatabase): WorkersDao {
        return workersDatabase.workerDao()
    }
    @Singleton
    @Provides
    fun providePayrollDAO(workersDatabase: WorkersDatabase): PayrollDao {
        return workersDatabase.payrollDao()
    }
}