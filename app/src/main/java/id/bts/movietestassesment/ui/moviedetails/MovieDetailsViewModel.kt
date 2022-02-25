package id.bts.movietestassesment.ui.moviedetails

import androidx.lifecycle.LiveData
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

    fun getMovieDetails(movieId: Long): LiveData<MovieDetailsResponse>{
        val movieDetailResponse: MutableLiveData<MovieDetailsResponse> = MutableLiveData()
        viewModelScope.launch {
            val response = repos.getMovieDetails(movieId)
            if(response.isSuccessful && response.body() != null){
                movieDetailResponse.value = response.body()
            }
        }
        return movieDetailResponse
    }

    fun getMovieVideos(movieId: Long): LiveData<MovieVideosResponse> {
        val movieVideoResponse: MutableLiveData<MovieVideosResponse> = MutableLiveData()
        viewModelScope.launch {
            val response = repos.getMovieVideos(movieId)
            if(response.isSuccessful && response.body() != null){
                movieVideoResponse.value = response.body()
            }
        }
        return movieVideoResponse
    }

    fun getMovieReviews(movieId: Long, page: Int): LiveData<MovieReviewResponse> {
        val movieReviewResponse: MutableLiveData<MovieReviewResponse> = MutableLiveData()
        viewModelScope.launch {
            val response = repos.getMovieReviews(movieId, page)
            if(response.isSuccessful && response.body() != null){
                movieReviewResponse.value = response.body()
            }
        }
        return movieReviewResponse
    }
}