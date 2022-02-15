package id.bts.movietestassesment.data.service

import id.bts.movietestassesment.data.dto.DiscoverByGenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverByGenreService {

    // Get all genres for movies
    @GET("discover/movie")
    suspend fun getAllMoviesByGenre(
        @Query("with_genres") genres: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ) : Response<DiscoverByGenreResponse>
}