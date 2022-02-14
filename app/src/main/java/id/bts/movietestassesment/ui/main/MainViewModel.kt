package id.bts.movietestassesment.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bts.movietestassesment.base.BaseViewModel
import id.bts.movietestassesment.data.dto.GenreListResponse
import id.bts.movietestassesment.domain.repository.genre.GenreRepositoryImpl
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repos: GenreRepositoryImpl
) : BaseViewModel() {

    private val TAG: String = MainViewModel::class.java.simpleName

    val genreResponse: MutableLiveData<Response<GenreListResponse>> = MutableLiveData()

    fun getAllMovieGenres() {
        viewModelScope.launch {
            val response = repos.getAllMovieGenres()
            genreResponse.value = response
            Log.d(TAG, "Success Fetch Data: ${response.body()!!.genre}")
        }
    }

}