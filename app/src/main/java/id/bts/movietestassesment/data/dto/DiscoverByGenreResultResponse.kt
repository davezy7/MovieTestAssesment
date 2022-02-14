package id.bts.movietestassesment.data.dto

import com.google.gson.annotations.SerializedName

data class DiscoverByGenreResultResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Float
)