package com.marcel.mycompany.ui

import android.app.Application
import android.view.LayoutInflater
import com.marcel.mycompany.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@HiltAndroidApp
class MyCompany : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}




