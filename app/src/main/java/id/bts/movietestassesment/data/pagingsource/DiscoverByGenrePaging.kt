package id.bts.movietestassesment.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.bts.movietestassesment.data.dto.DiscoverByGenreResultResponse
import id.bts.movietestassesment.data.service.DiscoverByGenreService
import javax.inject.Inject

class DiscoverByGenrePaging @Inject constructor(
    private val service: DiscoverByGenreService,
    private val genreId: Int
) : PagingSource<Int, DiscoverByGenreResultResponse>() {

    override fun getRefreshKey(state: PagingState<Int, DiscoverByGenreResultResponse>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverByGenreResultResponse> {
        val page = params.key ?: 1

        return try {
            val response = service.getAllMoviesByGenre(genreId, page)
            var data : List<DiscoverByGenreResultResponse> = arrayListOf()
            var totalPage = 1
            response.body()?.let {
                data = it.results
                totalPage = it.totalPages
            }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (page == totalPage) null else page.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}