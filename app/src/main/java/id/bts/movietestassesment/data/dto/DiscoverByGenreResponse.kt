package id.bts.movietestassesment.data.dto

import com.google.gson.annotations.SerializedName

data class DiscoverByGenreResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<DiscoverByGenreResultResponse>,
    @SerializedName("total_pages") val totalPages: Int
)