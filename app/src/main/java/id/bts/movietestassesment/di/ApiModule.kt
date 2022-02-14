package id.bts.movietestassesment.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import id.bts.movietestassesment.data.retrofit.RetrofitClient
import id.bts.movietestassesment.data.service.GenreService
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object ApiModule {

    @Provides
    @ViewModelScoped
    fun provideGenreService(@ApplicationContext context: Context): GenreService{
        return RetrofitClient(context).create(GenreService::class.java)
    }
}