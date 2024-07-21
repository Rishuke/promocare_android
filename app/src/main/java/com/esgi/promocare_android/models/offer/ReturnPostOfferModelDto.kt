package com.esgi.promocare_android.models.offer

data class ReturnPostOfferModelDto(
    val message : String,
    val item : OfferModelDto
)

data class OfferModelDto(
    val uuid: String?,
    val status: String?,
    val annonce_id: String?,
    val user_id: String?,
    val text: String?,
    val updated_at: String?,
    val created_at: String?
)

data class OfferModel(
    val uuid: String?,
    val status: String?,
    val annonce_id: String?,
    val user_id: String?,
    val text: String?,
    val updated_at: String?,
    val created_at: String?
)
