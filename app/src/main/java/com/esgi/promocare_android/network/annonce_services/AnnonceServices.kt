package com.esgi.promocare_android.network.annonce_services

import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AnnonceServices {
    @GET("annoncetoken")
    fun getAnnoncesCompany(@Header("Authorization") token: String): Call<ReturnAnnonceDto>
}