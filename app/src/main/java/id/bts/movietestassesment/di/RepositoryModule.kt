package id.bts.movietestassesment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.bts.movietestassesment.data.service.DiscoverByGenreService
import id.bts.movietestassesment.data.service.GenreService
import id.bts.movietestassesment.domain.repository.discoverbygenre.DiscoverByGenreRepository
import id.bts.movietestassesment.domain.repository.discoverbygenre.DiscoverByGenreRepositoryImpl
import id.bts.movietestassesment.domain.repository.genre.GenreRepository
import id.bts.movietestassesment.domain.repository.genre.GenreRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideGenreRepository(service: GenreService, apiKey: String): GenreRepository {
        return GenreRepositoryImpl(service, apiKey)
    }

    @Provides
    @ViewModelScoped
    fun provideDiscoverByGenreRepository(service: DiscoverByGenreService, apiKey: String): DiscoverByGenreRepository{
        return DiscoverByGenreRepositoryImpl(service, apiKey)
    }
}