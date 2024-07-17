package com.esgi.promocare_android.network.annonce_services

import com.esgi.promocare_android.models.annonce.CreateAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnAllAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnCreateAnnonceDto
import retrofit2.Call

class AnnonceRepository(private val annonceServices: AnnonceServices) {
    fun getAnnoncesCompany(token: String): Call<ReturnAnnonceDto> {
        return annonceServices.getAnnoncesCompany(token)
    }

    fun getAllAnnonce(token: String): Call<ReturnAllAnnonceDto> {
        return annonceServices.getAllAnnonce(token)
    }

    fun postAnnonceCompany(token: String,createAnnonce: CreateAnnonceDto): Call<ReturnCreateAnnonceDto>{
        return annonceServices.postAnnonceCompany(token,createAnnonce)
    }
}