package id.bts.movietestassesment.domain.repository.genre

import id.bts.movietestassesment.data.dto.GenreListResponse
import retrofit2.Response

interface GenreRepository {

    suspend fun getAllMovieGenres(): Response<GenreListResponse>
}