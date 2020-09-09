package com.marcel.mycompany.di

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.marcel.mycompany.R
import com.marcel.mycompany.databinding.ActivityMainBinding
import com.marcel.mycompany.databinding.WorkersFragmentBinding
import com.marcel.mycompany.room.PayrollDao
import com.marcel.mycompany.room.WorkersDao
import com.marcel.mycompany.room.WorkersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
object MainModule {
    @Singleton
    @Provides
    fun provideMainActivityBinding(layoutInflater: LayoutInflater)
            : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


}
