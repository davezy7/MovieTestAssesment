package id.bts.movietestassesment.data.service

import id.bts.movietestassesment.data.dto.GenreListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {

    // Get all genres for movies
    @GET("genre/movie/list")
    suspend fun getAllMovieGenres(
        @Query("api_key") apiKey: String
    ) : Response<GenreListResponse>
}