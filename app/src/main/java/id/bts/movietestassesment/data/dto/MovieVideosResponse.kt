package id.bts.movietestassesment.data.dto

import com.google.gson.annotations.SerializedName

data class MovieVideosResponse(
    @SerializedName("results") val results: List<MovieVideosResultResponse>
)