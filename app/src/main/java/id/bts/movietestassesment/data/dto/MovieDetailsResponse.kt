package id.bts.movietestassesment.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genres") val genres: List<GenreResponse>,
    @SerializedName("id") val id: Long,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Float
)