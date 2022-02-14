package id.bts.movietestassesment.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.bts.movietestassesment.utils.Constants.API_KEY
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application) : Context{
        return application
    }

    @Provides
    @Singleton
    fun provideApiKey(): String { return API_KEY }
}