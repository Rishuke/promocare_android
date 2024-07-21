package com.esgi.promocare_android.models.company

data class CompanyModelDto(
    val company_name: String?,
    val created_at: String?,
    val email: String?,
    val location: String?,
    val phone: String?,
    val role: String?,
    val siret_number: String?,
    val updated_at: String?,
    val uuid: String?
)

data class CompanyModel(
    val company_name: String?,
    val created_at: String?,
    val email: String?,
    val location: String?,
    val phone: String?,
    val role: String?,
    val siret_number: String?,
    val updated_at: String?,
    val uuid: String?
)