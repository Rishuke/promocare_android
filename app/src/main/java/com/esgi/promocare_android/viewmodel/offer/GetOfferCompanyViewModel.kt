package com.esgi.promocare_android.viewmodel.offer

import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.offer.AllOfferCompany
import com.esgi.promocare_android.models.offer.GetOfferCompany
import com.esgi.promocare_android.models.offer.GetOfferCompanyDto
import com.esgi.promocare_android.models.offer.OfferModel
import com.esgi.promocare_android.models.user.UserModel
import com.esgi.promocare_android.network.offer_services.OfferRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetOfferCompanyViewModel(private val offerRepository: OfferRepository){

    var offerList: MutableLiveData<ArrayList<GetOfferCompany>> = MutableLiveData()

    private fun handleEnqueu(apiResponse : Call<AllOfferCompany>,loader:ProgressBar,noResult:TextView,error:TextView){
        offerList.value = ArrayList()
        apiResponse.enqueue(object : Callback<AllOfferCompany> {
            override fun onFailure(p0: Call<AllOfferCompany>, t: Throwable) {
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<AllOfferCompany>, response: Response<AllOfferCompany>) {
                val responseBody: List<GetOfferCompanyDto> = response.body()?.offers ?: listOf()
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

                    val user = UserModel(
                        it.user.createdAt,
                        it.user.email,
                        it.user.firstName,
                        it.user.lastName,
                        it.user.phone,
                        it.user.role,
                        it.user.updatedAt,
                        it.user.uuid,
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



                    GetOfferCompany(
                        offer,
                        user,
                        annonce
                    )
                }

                if(mappedResponse.isEmpty()){
                    loader.visibility = ProgressBar.GONE
                    noResult.visibility = TextView.VISIBLE
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

    fun getOfferCompanyPending(token: String,loader: ProgressBar,noResult: TextView,error: TextView){
        val apiResponse = offerRepository.getOfferCompanyPending(token)
        loader.visibility = ProgressBar.VISIBLE
        handleEnqueu(apiResponse,loader,noResult,error)
    }

    fun getOfferCompanyAccepted(token: String,loader: ProgressBar,noResult: TextView,error: TextView){
        val apiResponse = offerRepository.getOfferCompanyAccepted(token)
        loader.visibility = ProgressBar.VISIBLE
        handleEnqueu(apiResponse,loader,noResult,error)
    }

    fun getOfferCompanyRefused(token: String,loader: ProgressBar,noResult: TextView,error: TextView){
        val apiResponse = offerRepository.getOfferCompanyRefused(token)
        loader.visibility = ProgressBar.VISIBLE
        handleEnqueu(apiResponse,loader,noResult,error)
    }
}