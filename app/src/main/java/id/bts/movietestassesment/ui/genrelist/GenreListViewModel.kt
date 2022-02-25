package id.bts.movietestassesment.ui.genrelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bts.movietestassesment.data.dto.GenreListResponse
import id.bts.movietestassesment.domain.repository.genre.GenreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreListViewModel @Inject constructor(
    private val repos: GenreRepository
) : ViewModel() {

    fun getAllMovieGenres(): LiveData<GenreListResponse> {
        val genreResponse: MutableLiveData<GenreListResponse> = MutableLiveData()
        viewModelScope.launch {
            val response = repos.getAllMovieGenres()
            if(response.isSuccessful && response.body() != null){
                genreResponse.value = response.body()
            }
        }
        return genreResponse
    }

}