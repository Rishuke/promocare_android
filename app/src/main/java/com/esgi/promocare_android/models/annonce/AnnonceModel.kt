package com.esgi.promocare_android.models.annonce

data class AnnonceModel(
    val uuid: String? = null,
    val companyId: String? = null,
    val location: String? = null,
    val price: Float? = null,
    val promo: Int? = null,
    val status: String? = null,
    val title: String? = null,
    val description:String? = null,
    val type: String? = null,
    val viewTime: Int? = null,
    val updatedAt: String? = null,
    val createdAt: String? = null,
)
