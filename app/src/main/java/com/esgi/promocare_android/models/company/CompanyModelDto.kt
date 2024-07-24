package com.esgi.promocare_android.models.company

import com.google.gson.annotations.SerializedName

data class CompanyModelDto(
    @SerializedName(value = "company_name") val companyName: String?,
    @SerializedName(value = "created_at")val createdAt: String?,
    val email: String?,
    val location: String?,
    val phone: String?,
    val role: String?,
    @SerializedName(value = "siret_number")val siretNumber: String?,
    @SerializedName(value = "updated_at")val updatedAt: String?,
    val uuid: String?
)

