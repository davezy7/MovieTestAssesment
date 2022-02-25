package id.bts.movietestassesment.domain.repository.discoverbygenre

import androidx.paging.PagingData
import id.bts.movietestassesment.data.dto.DiscoverByGenreResultResponse
import kotlinx.coroutines.flow.Flow

interface DiscoverByGenreRepository {

    suspend fun getAllMoviesByGenre(
        genre: Int
    ): Flow<PagingData<DiscoverByGenreResultResponse>>
}