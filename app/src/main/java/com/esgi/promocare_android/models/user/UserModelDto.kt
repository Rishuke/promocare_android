package com.esgi.promocare_android.models.user

import com.google.gson.annotations.SerializedName

data class UserModelDto(
    @SerializedName(value = "created_at")val createdAt: String?,
    val email: String?,
    @SerializedName(value = "first_name")val firstName: String?,
    @SerializedName(value = "last_name")val lastName: String?,
    val phone: String?,
    val role: String?,
    @SerializedName(value = "updated_at")val updatedAt: String?,
    val uuid: String?
)

