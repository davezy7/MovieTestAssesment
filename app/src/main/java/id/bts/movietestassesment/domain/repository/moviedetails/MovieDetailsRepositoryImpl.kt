package id.bts.movietestassesment.domain.repository.moviedetails

import id.bts.movietestassesment.data.dto.MovieDetailsResponse
import id.bts.movietestassesment.data.dto.MovieReviewResponse
import id.bts.movietestassesment.data.dto.MovieVideosResponse
import id.bts.movietestassesment.data.service.MovieDetailsService
import retrofit2.Response
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val service: MovieDetailsService,
    private val apiKey: String
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Long): Response<MovieDetailsResponse> {
        return service.getMovieDetails(movieId, apiKey)
    }

    override suspend fun getMovieVideos(movieId: Long): Response<MovieVideosResponse> {
        return service.getMovieVideos(movieId, apiKey)
    }

    override suspend fun getMovieReviews(movieId: Long, page: Int): Response<MovieReviewResponse> {
        return service.getMovieReviews(movieId, page, apiKey)
    }
}