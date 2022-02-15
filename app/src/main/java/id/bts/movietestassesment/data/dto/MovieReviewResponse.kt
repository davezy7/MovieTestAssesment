package id.bts.movietestassesment.data.dto

import com.google.gson.annotations.SerializedName

data class MovieReviewResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieReviewResultResponse>,
    @SerializedName("total_pages") val totalPages: Int
)