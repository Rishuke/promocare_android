package com.esgi.promocare_android.models.offer

import com.google.gson.annotations.SerializedName

data class ReturnPostOfferModelDto(
    val message : String,
    val item : OfferModelDto
)

data class OfferModelDto(
    val uuid: String?,
    val status: String?,
    @SerializedName(value = "annonce_id")val annonceId: String?,
    @SerializedName(value = "user_id")val userId: String?,
    val text: String?,
    @SerializedName(value = "updated_at")val updatedAt: String?,
    @SerializedName(value = "created_at")val createdAt: String?
)
