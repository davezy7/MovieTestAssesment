package id.bts.movietestassesment.domain.repository.moviedetails

import id.bts.movietestassesment.data.dto.MovieDetailsResponse
import id.bts.movietestassesment.data.dto.MovieReviewResponse
import id.bts.movietestassesment.data.dto.MovieVideosResponse
import retrofit2.Response

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Long): Response<MovieDetailsResponse>

    suspend fun getMovieVideos(movieId: Long): Response<MovieVideosResponse>

    suspend fun getMovieReviews(movieId: Long, page: Int): Response<MovieReviewResponse>
}