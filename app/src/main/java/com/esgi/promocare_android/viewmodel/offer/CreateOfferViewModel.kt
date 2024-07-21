package com.esgi.promocare_android.viewmodel.offer

import com.esgi.promocare_android.models.offer.PostOfferModel
import com.esgi.promocare_android.models.offer.ReturnPostOfferModelDto
import com.esgi.promocare_android.network.offer_services.OfferRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateOfferViewModel(private val offerRepository: OfferRepository) {
    var annonceId: String = ""
    var userId: String = ""
    var startDate: String = ""
    var endDate: String = ""
    var frequence: String = ""
    var location: String = ""
    var nbSeance: String = ""
    var price: String = ""
    var commentaire: String = ""

    fun createOffer(token:String, offer: PostOfferModel){
        val apiResponse = offerRepository.postOffer(token, offer)

        apiResponse.enqueue(object : Callback<ReturnPostOfferModelDto> {
            override fun onFailure(p0: Call<ReturnPostOfferModelDto>, t: Throwable) {
                /*loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE*/
            }
            override fun onResponse(p0: Call<ReturnPostOfferModelDto>, response: Response<ReturnPostOfferModelDto>) {
                /*loader.visibility = ProgressBar.GONE
                error.visibility = TextView.GONE*/
            }
        })
    }
}
