package com.esgi.promocare_android.models.inscription

import com.google.gson.annotations.SerializedName

data class SubscribeCompanyRequest(
    val email : String,
    val password : String,
    @SerializedName(value = "company_name")val companyName : String,
    @SerializedName(value = "siret_number")val siretNumber : String,
    val location : String)