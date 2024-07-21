package com.esgi.promocare_android.network.annonce_services

import com.esgi.promocare_android.models.annonce.CreateAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnAllAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnCreateAnnonceDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AnnonceServices {
    @GET("annoncetoken")
    fun getAnnoncesCompany(@Header("Authorization") token: String): Call<ReturnAnnonceDto>

    @GET("annonce")
    fun getAllAnnonce(@Header("Authorization") token: String): Call<ReturnAllAnnonceDto>

    @POST("annonceid")
    fun postAnnonceCompany(@Header("Authorization") token: String,@Body createAnnonce:CreateAnnonceDto): Call<ReturnCreateAnnonceDto>

    @PATCH("annonce/{annonceId}")
    fun updateAnnonceCompany(
        @Header("Authorization") token: String,
        @Path("annonceId") annonceId: String,
        @Body createAnnonce: CreateAnnonceDto
    ): Call<ReturnCreateAnnonceDto>

    @DELETE("annonce/{annonceId}")
    fun deleteAnnonceCompany(
        @Header("Authorization") token: String,
        @Path("annonceId") annonceId: String
    ): Call<ReturnCreateAnnonceDto>

}