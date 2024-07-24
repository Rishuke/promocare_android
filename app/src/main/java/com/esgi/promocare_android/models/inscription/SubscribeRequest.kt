package com.esgi.promocare_android.models.inscription

import com.google.gson.annotations.SerializedName

data class SubscribeRequest(
    val email : String,
    val password : String,
    @SerializedName(value = "first_name")val firstName : String,
    @SerializedName(value = "last_name")val lastName : String
)

