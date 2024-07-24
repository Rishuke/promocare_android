package com.esgi.promocare_android.models.annonce

import com.google.gson.annotations.SerializedName

data class ReturnAllAnnonceDto(
    val message : String?,
    val items: List<AnnonceDto>?
)

data class ReturnAnnonceDto(
    val message : String?,
    val item: List<AnnonceDto>?
)

data class ReturnCreateAnnonceDto(
    val message : String?,
    val item: AnnonceDto?
)

data class AnnonceDto(
    val uuid: String? = null,
    @SerializedName(value = "company_id") val companyId: String? = null,
    val location: String? = null,
    val price: Float? = null,
    val promo: Int? = null,
    val status: String? = null,
    val title: String? = null,
    val description:String? = null,
    val type: String? = null,
    @SerializedName(value = "view_time") val viewTime: Int? = null,
    @SerializedName(value = "updated_at")val updatedAt: String? = null,
    @SerializedName(value = "created_at")val createdAt: String? = null,
)
