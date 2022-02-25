package id.bts.movietestassesment.domain.repository.discoverbygenre

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import id.bts.movietestassesment.data.dto.DiscoverByGenreResponse
import id.bts.movietestassesment.data.dto.DiscoverByGenreResultResponse
import id.bts.movietestassesment.data.pagingsource.DiscoverByGenrePaging
import id.bts.movietestassesment.data.service.DiscoverByGenreService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class DiscoverByGenreRepositoryImpl @Inject constructor(
    private val service: DiscoverByGenreService
    ): DiscoverByGenreRepository{

    override suspend fun getAllMoviesByGenre(genre: Int): Flow<PagingData<DiscoverByGenreResultResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { DiscoverByGenrePaging(service, genre) }
        ).flow
    }

}