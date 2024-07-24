package com.esgi.promocare_android.viewmodel.offer

import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.company.CompanyModel
import com.esgi.promocare_android.models.offer.AllOfferUser
import com.esgi.promocare_android.models.offer.GetOfferUser
import com.esgi.promocare_android.models.offer.GetOfferUserDto
import com.esgi.promocare_android.models.offer.OfferModel
import com.esgi.promocare_android.models.offer.PatchOffer
import com.esgi.promocare_android.models.offer.PatchOfferResponse
import com.esgi.promocare_android.network.offer_services.OfferRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetOfferUserViewModel(private val offerRepository: OfferRepository) {

    var offerList: MutableLiveData<ArrayList<GetOfferUser>> = MutableLiveData()

    private fun handleEnqueu(apiResponse : Call<AllOfferUser>, loader: ProgressBar, noResult: TextView, error: TextView){
        offerList.value = ArrayList()
        apiResponse.enqueue(object : Callback<AllOfferUser> {
            override fun onFailure(p0: Call<AllOfferUser>, t: Throwable) {
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<AllOfferUser>, response: Response<AllOfferUser>) {
                val responseBody: List<GetOfferUserDto> = response.body()?.offers ?: listOf()
                val mappedResponse = responseBody.map {
                    val offer = OfferModel(
                        it.offer.uuid,
                        it.offer.status,
                        it.offer.annonceId,
                        it.offer.userId,
                        it.offer.text,
                        it.offer.updatedAt,
                        it.offer.createdAt,
                    )

                    val company = CompanyModel(
                        it.company.companyName,
                        it.company.createdAt,
                        it.company.email,
                        it.company.location,
                        it.company.phone,
                        it.company.role,
                        it.company.siretNumber,
                        it.company.updatedAt,
                        it.company.uuid,
                    )

                    val annonce = AnnonceModel(
                        it.annonce.uuid,
                        it.annonce.companyId,
                        it.annonce.location,
                        it.annonce.price,
                        it.annonce.promo,
                        it.annonce.status,
                        it.annonce.title,
                        it.annonce.description,
                        it.annonce.type,
                        it.annonce.viewTime,
                        it.annonce.updatedAt,
                        it.annonce.createdAt,
                    )

                    GetOfferUser(
                        offer,
                        company,
                        annonce
                    )
                }
                if(mappedResponse.isEmpty()){
                    noResult.visibility = TextView.VISIBLE
                    loader.visibility = ProgressBar.GONE
                }
                else{
                    offerList.value = ArrayList(mappedResponse)
                    loader.visibility = ProgressBar.GONE
                    error.visibility = TextView.GONE
                    noResult.visibility = TextView.GONE
                }

            }
        })
    }

    fun getOfferUserPending(token: String, loader: ProgressBar, noResult: TextView, error: TextView) {
        val apiResponse = offerRepository.getOfferUserPending(token)
        loader.visibility = ProgressBar.VISIBLE
        handleEnqueu(apiResponse, loader, noResult, error)
    }

    fun getOfferUserAccepted(token: String, loader: ProgressBar, noResult: TextView, error: TextView) {
        val apiResponse = offerRepository.getOfferUserAccepted(token)
        loader.visibility = ProgressBar.VISIBLE
        handleEnqueu(apiResponse, loader, noResult, error)
    }

    fun getOfferUserRefused(token: String, loader: ProgressBar, noResult: TextView, error: TextView) {
        val apiResponse = offerRepository.getOfferUserRefused(token)
        loader.visibility = ProgressBar.VISIBLE
        handleEnqueu(apiResponse, loader, noResult, error)
    }

    fun patchOffer(token: String, patchOffer: PatchOffer, offerId: String) {
        val apiResponse = offerRepository.patchOffer(token, patchOffer, offerId)
        apiResponse.enqueue(object : Callback<PatchOfferResponse> {
            override fun onFailure(p0: Call<PatchOfferResponse>, t: Throwable) {
                /*loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE*/
            }

            override fun onResponse(p0: Call<PatchOfferResponse>, response: Response<PatchOfferResponse>) {

            }
        })
    }
}