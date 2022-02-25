package id.bts.movietestassesment.data.service

import id.bts.movietestassesment.data.dto.MovieDetailsResponse
import id.bts.movietestassesment.data.dto.MovieReviewResponse
import id.bts.movietestassesment.data.dto.MovieVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailsService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long
    ) : Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Long
    ) : Response<MovieVideosResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Long,
        @Query("page") page: Int
    ) : Response<MovieReviewResponse>
}