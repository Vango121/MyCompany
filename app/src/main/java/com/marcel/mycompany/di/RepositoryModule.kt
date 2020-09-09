package com.marcel.mycompany.di

import android.app.Application
import android.content.Context
import com.marcel.mycompany.repository.RepositoryCl
import com.marcel.mycompany.repository.SharedPreferences
import com.marcel.mycompany.room.PayrollDao
import com.marcel.mycompany.room.WorkersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        @ApplicationContext context: Context,
        workersDao: WorkersDao,
        payrollDao: PayrollDao,
        sharedPreferences: SharedPreferences
    ): RepositoryCl{
        return RepositoryCl(context,workersDao,payrollDao,sharedPreferences)
    }
}
