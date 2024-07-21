package com.esgi.promocare_android.network.offer_services

import com.esgi.promocare_android.models.offer.PostOfferModel
import com.esgi.promocare_android.models.offer.ReturnPostOfferModelDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OfferServices {
    @POST("offer")
    fun postOffer(
        @Header("Authorization") token: String,
        @Body postOfferModel: PostOfferModel
    ): Call<ReturnPostOfferModelDto>
}