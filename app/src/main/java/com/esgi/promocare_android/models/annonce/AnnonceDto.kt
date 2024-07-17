package com.esgi.promocare_android.models.annonce

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
    val company_id: String? = null,
    val location: String? = null,
    val price: Float? = null,
    val promo: Int? = null,
    val status: String? = null,
    val title: String? = null,
    val description:String? = null,
    val type: String? = null,
    val view_time: Int? = null,
    val updated_at: String? = null,
    val created_at: String? = null,
)
