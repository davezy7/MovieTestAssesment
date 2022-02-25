package id.bts.movietestassesment.ui.discoverbygenre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bts.movietestassesment.base.BaseViewModel
import id.bts.movietestassesment.data.dto.DiscoverByGenreResponse
import id.bts.movietestassesment.domain.repository.discoverbygenre.DiscoverByGenreRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DiscoverByGenreViewModel @Inject constructor(
    private val repos: DiscoverByGenreRepository
) : BaseViewModel() {

    fun getAllMoviesByGenre(genre: Int, page: Int): LiveData<DiscoverByGenreResponse> {
        val movieResponse: MutableLiveData<DiscoverByGenreResponse> = MutableLiveData()
        viewModelScope.launch {
            val response = repos.getAllMoviesByGenre(genre, page)
            if(response.isSuccessful && response.body() != null){
                movieResponse.value = response.body()
            }
        }
        return movieResponse
    }
}