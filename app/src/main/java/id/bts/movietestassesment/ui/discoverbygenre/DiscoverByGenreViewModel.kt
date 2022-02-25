package id.bts.movietestassesment.ui.discoverbygenre

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import id.bts.movietestassesment.data.dto.DiscoverByGenreResponse
import id.bts.movietestassesment.data.dto.DiscoverByGenreResultResponse
import id.bts.movietestassesment.domain.repository.discoverbygenre.DiscoverByGenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverByGenreViewModel @Inject constructor(
    private val repos: DiscoverByGenreRepository
) : ViewModel() {

    suspend fun getAllMoviesByGenre(genre: Int): Flow<PagingData<DiscoverByGenreResultResponse>> {
        return repos.getAllMoviesByGenre(genre).cachedIn(viewModelScope)
    }
}