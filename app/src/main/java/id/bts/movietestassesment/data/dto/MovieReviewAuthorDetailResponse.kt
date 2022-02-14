package id.bts.movietestassesment.data.dto

import com.google.gson.annotations.SerializedName

data class MovieReviewAuthorDetailResponse(
    @SerializedName("avatar_path") val avatarPath: String?,
    @SerializedName("rating") val rating: Float?
)