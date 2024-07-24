package com.esgi.promocare_android.models.offer

import com.google.gson.annotations.SerializedName

data class PostOfferModel(
    @SerializedName(value = "annonce_id")val annonceId : String,
    @SerializedName(value = "user_id")val userId : String,
    val text : String
)
