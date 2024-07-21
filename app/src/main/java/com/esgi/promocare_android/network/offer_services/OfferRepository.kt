package com.esgi.promocare_android.network.offer_services

import com.esgi.promocare_android.models.offer.PostOfferModel
import com.esgi.promocare_android.models.offer.ReturnPostOfferModelDto
import retrofit2.Call

class OfferRepository(private val offerServices: OfferServices) {
    fun postOffer(token: String, postOfferModel: PostOfferModel): Call<ReturnPostOfferModelDto> {
       return offerServices.postOffer(token, postOfferModel)
    }
}