package id.bts.movietestassesment.data.dto

import com.google.gson.annotations.SerializedName

data class MovieReviewResultResponse(
    @SerializedName("author") val author: String,
    @SerializedName("author_details") val authorDetails: MovieReviewAuthorDetailResponse,
    @SerializedName("content") val content: String,
    @SerializedName("created_at") val createdAt: String,
)
