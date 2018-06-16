package com.raccoon.fakeindicator.di

import com.raccoon.fakeindicator.MonitorActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun buildMonitorActivity(): MonitorActivity

}