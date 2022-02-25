package id.bts.movietestassesment.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import id.bts.movietestassesment.data.retrofit.RetrofitClient
import id.bts.movietestassesment.data.service.DiscoverByGenreService
import id.bts.movietestassesment.data.service.GenreService
import id.bts.movietestassesment.data.service.MovieDetailsService
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object ApiModule {

    @Provides
    @ViewModelScoped
    fun provideGenreService(): GenreService{
        return RetrofitClient().create(GenreService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideDiscoverByGenreService(): DiscoverByGenreService{
        return RetrofitClient().create(DiscoverByGenreService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideMovieDetailsService(): MovieDetailsService{
        return RetrofitClient().create(MovieDetailsService::class.java)
    }
}