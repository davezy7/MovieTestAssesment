package id.bts.movietestassesment.ui.moviedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bts.movietestassesment.base.BaseViewModel
import id.bts.movietestassesment.data.dto.MovieDetailsResponse
import id.bts.movietestassesment.data.dto.MovieReviewResponse
import id.bts.movietestassesment.data.dto.MovieVideosResponse
import id.bts.movietestassesment.domain.repository.moviedetails.MovieDetailsRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repos: MovieDetailsRepository
) : BaseViewModel() {

    val movieDetailResponse: MutableLiveData<Response<MovieDetailsResponse>> = MutableLiveData()
    val movieVideoResponse: MutableLiveData<Response<MovieVideosResponse>> = MutableLiveData()
    val movieReviewResponse: MutableLiveData<Response<MovieReviewResponse>> = MutableLiveData()

    fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            val response = repos.getMovieDetails(movieId)
            movieDetailResponse.value = response
        }
    }

    fun getMovieVideos(movieId: Long){
        viewModelScope.launch {
            val response = repos.getMovieVideos(movieId)
            movieVideoResponse.value = response
        }
    }

    fun getMovieReviews(movieId: Long){
        viewModelScope.launch {
            val response = repos.getMovieReviews(movieId)
            movieReviewResponse.value = response
        }
    }
}