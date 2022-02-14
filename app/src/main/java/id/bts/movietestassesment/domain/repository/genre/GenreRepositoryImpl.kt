package id.bts.movietestassesment.domain.repository.genre

import android.util.Log
import id.bts.movietestassesment.data.dto.GenreListResponse
import id.bts.movietestassesment.data.service.GenreService
import retrofit2.Response
import javax.inject.Inject

class GenreRepositoryImpl  @Inject constructor(
    private val service: GenreService,
    private val apiKey: String): GenreRepository{


    override suspend fun getAllMovieGenres(): Response<GenreListResponse> {
        val genreList = service.getAllMovieGenres(apiKey)
        Log.d("REPO", "FETCH : ${genreList.body()?.genre}")
        return genreList
    }

}