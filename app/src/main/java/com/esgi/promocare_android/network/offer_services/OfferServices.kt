package com.esgi.promocare_android.network.offer_services

import com.esgi.promocare_android.models.offer.AllOfferCompany
import com.esgi.promocare_android.models.offer.AllOfferUser
import com.esgi.promocare_android.models.offer.PostOfferModel
import com.esgi.promocare_android.models.offer.ReturnPostOfferModelDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OfferServices {
    @POST("offer")
    fun postOffer(
        @Header("Authorization") token: String,
        @Body postOfferModel: PostOfferModel
    ): Call<ReturnPostOfferModelDto>

    @GET("getOfferCompanyPending")
    fun getOfferCompanyPending(
        @Header("Authorization") token: String
    ): Call<AllOfferCompany>

    @GET("getOfferCompanyAccepted")
    fun getOfferCompanyAccepted(
        @Header("Authorization") token: String
    ): Call<AllOfferCompany>

    @GET("getOfferCompanyRefused")
    fun getOfferCompanyRefused(
        @Header("Authorization") token: String
    ): Call<AllOfferCompany>

    @GET("getOfferUserPending")
    fun getOfferUserPending(
        @Header("Authorization") token: String
    ): Call<AllOfferUser>

    @GET("getOfferUserAccepted")
    fun getOfferUserAccepted(
        @Header("Authorization") token: String
    ): Call<AllOfferUser>

    @GET("getOfferUserRefused")
    fun getOfferUserRefused(
        @Header("Authorization") token: String
    ): Call<AllOfferUser>
}