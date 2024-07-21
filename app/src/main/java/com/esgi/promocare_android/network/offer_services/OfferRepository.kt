package com.esgi.promocare_android.network.offer_services

import com.esgi.promocare_android.models.offer.AllOfferCompany
import com.esgi.promocare_android.models.offer.AllOfferUser
import com.esgi.promocare_android.models.offer.PostOfferModel
import com.esgi.promocare_android.models.offer.ReturnPostOfferModelDto
import retrofit2.Call

class OfferRepository(private val offerServices: OfferServices) {
    fun postOffer(token: String, postOfferModel: PostOfferModel): Call<ReturnPostOfferModelDto> {
       return offerServices.postOffer(token, postOfferModel)
    }

    fun getOfferCompanyPending(token: String): Call<AllOfferCompany>{
        return offerServices.getOfferCompanyPending(token)
    }

    fun getOfferCompanyAccepted(token: String): Call<AllOfferCompany>{
        return offerServices.getOfferCompanyAccepted(token)
    }

    fun getOfferCompanyRefused(token: String): Call<AllOfferCompany>{
        return offerServices.getOfferCompanyRefused(token)
    }

    fun getOfferUserPending(token: String): Call<AllOfferUser>{
        return offerServices.getOfferUserPending(token)
    }

    fun getOfferUserAccepted(token: String): Call<AllOfferUser>{
        return offerServices.getOfferUserAccepted(token)
    }

    fun getOfferUserRefused(token: String): Call<AllOfferUser>{
        return offerServices.getOfferUserRefused(token)
    }
}