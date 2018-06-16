package com.raccoon.fakeindicator.di

import android.app.Application
import android.content.Context
import com.raccoon.fakeindicator.BusinessModel
import com.raccoon.fakeindicator.provider.DataProvider
import com.raccoon.fakeindicator.schedulers.ISchedulers
import com.raccoon.fakeindicator.schedulers.WorkSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideScheduler(): ISchedulers = WorkSchedulers

    @Provides
    @Singleton
    fun provideDataProvider(): DataProvider = DataProvider()

    @Provides
    @Singleton
    fun provideBusinessModel(dataProvider: DataProvider, schedulers: ISchedulers): BusinessModel = BusinessModel(dataProvider, schedulers)

}
