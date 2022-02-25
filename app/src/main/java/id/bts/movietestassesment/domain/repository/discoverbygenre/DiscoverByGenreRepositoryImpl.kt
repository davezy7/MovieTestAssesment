package id.bts.movietestassesment.domain.repository.discoverbygenre

import id.bts.movietestassesment.data.dto.DiscoverByGenreResponse
import id.bts.movietestassesment.data.service.DiscoverByGenreService
import retrofit2.Response
import javax.inject.Inject

class DiscoverByGenreRepositoryImpl @Inject constructor(
    private val service: DiscoverByGenreService
    ): DiscoverByGenreRepository{

    override suspend fun getAllMoviesByGenre(genre: Int, page: Int): Response<DiscoverByGenreResponse> {
        return service.getAllMoviesByGenre(genres = genre, page = page)
    }

}