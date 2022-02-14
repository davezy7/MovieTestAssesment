package id.bts.movietestassesment.domain.repository.discoverbygenre

import id.bts.movietestassesment.data.dto.DiscoverByGenreResponse
import retrofit2.Response

interface DiscoverByGenreRepository {

    suspend fun getAllMoviesByGenre(genre: Int): Response<DiscoverByGenreResponse>
}