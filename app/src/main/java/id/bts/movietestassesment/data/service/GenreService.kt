package id.bts.movietestassesment.data.service

import id.bts.movietestassesment.data.dto.GenreListResponse
import id.bts.movietestassesment.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GenreService {

    // Get all genres for movies
    @GET("genre/movie/list")
    suspend fun getAllMovieGenres() : Response<GenreListResponse>
}