package id.bts.movietestassesment.domain.repository.moviedetails

import id.bts.movietestassesment.data.dto.MovieDetailsResponse
import id.bts.movietestassesment.data.dto.MovieReviewResponse
import id.bts.movietestassesment.data.dto.MovieVideosResponse
import id.bts.movietestassesment.data.service.MovieDetailsService
import retrofit2.Response
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val service: MovieDetailsService) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Long): Response<MovieDetailsResponse> {
        return service.getMovieDetails(movieId)
    }

    override suspend fun getMovieVideos(movieId: Long): Response<MovieVideosResponse> {
        return service.getMovieVideos(movieId)
    }

    override suspend fun getMovieReviews(movieId: Long, page: Int): Response<MovieReviewResponse> {
        return service.getMovieReviews(movieId, page)
    }
}