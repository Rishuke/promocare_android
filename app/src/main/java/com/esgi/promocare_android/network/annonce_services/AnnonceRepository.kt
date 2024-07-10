package com.esgi.promocare_android.network.annonce_services

import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import retrofit2.Call

class AnnonceRepository(private val annonceServices: AnnonceServices) {
    fun getAnnoncesCompany(token: String): Call<ReturnAnnonceDto> {
        return annonceServices.getAnnoncesCompany(token)
    }
}