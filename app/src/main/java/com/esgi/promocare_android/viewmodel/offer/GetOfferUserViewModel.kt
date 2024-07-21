package com.esgi.promocare_android.viewmodel.offer

import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.company.CompanyModel
import com.esgi.promocare_android.models.offer.AllOfferCompany
import com.esgi.promocare_android.models.offer.AllOfferUser
import com.esgi.promocare_android.models.offer.GetOfferCompany
import com.esgi.promocare_android.models.offer.GetOfferCompanyDto
import com.esgi.promocare_android.models.offer.GetOfferUser
import com.esgi.promocare_android.models.offer.GetOfferUserDto
import com.esgi.promocare_android.models.offer.OfferModel
import com.esgi.promocare_android.models.user.UserModel
import com.esgi.promocare_android.network.offer_services.OfferRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetOfferUserViewModel(private val offerRepository: OfferRepository) {

    var offerList: MutableLiveData<ArrayList<GetOfferUser>> = MutableLiveData()

    private fun handleEnqueu(apiResponse : Call<AllOfferUser>, loader: TextView, noResult: TextView, error: TextView){
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
                        it.offer.annonce_id,
                        it.offer.user_id,
                        it.offer.text,
                        it.offer.updated_at,
                        it.offer.created_at,
                    )

                    val company = CompanyModel(
                        it.company.company_name,
                        it.company.created_at,
                        it.company.email,
                        it.company.location,
                        it.company.phone,
                        it.company.role,
                        it.company.siret_number,
                        it.company.updated_at,
                        it.company.uuid,
                    )

                    val annonce = AnnonceModel(
                        it.annonce.uuid,
                        it.annonce.company_id,
                        it.annonce.location,
                        it.annonce.price,
                        it.annonce.promo,
                        it.annonce.status,
                        it.annonce.title,
                        it.annonce.description,
                        it.annonce.type,
                        it.annonce.view_time,
                        it.annonce.updated_at,
                        it.annonce.created_at,
                    )

                    GetOfferUser(
                        offer,
                        company,
                        annonce
                    )
                }
                offerList.value = ArrayList(mappedResponse)
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.GONE
            }
        })
    }

    fun getOfferUserPending(token: String, loader: TextView, noResult: TextView, error: TextView) {
        val apiResponse = offerRepository.getOfferUserPending(token)
        handleEnqueu(apiResponse, loader, noResult, error)
    }

    fun getOfferUserAccepted(token: String, loader: TextView, noResult: TextView, error: TextView) {
        val apiResponse = offerRepository.getOfferUserAccepted(token)
        handleEnqueu(apiResponse, loader, noResult, error)
    }

    fun getOfferUserRefused(token: String, loader: TextView, noResult: TextView, error: TextView) {
        val apiResponse = offerRepository.getOfferUserRefused(token)
        handleEnqueu(apiResponse, loader, noResult, error)
    }
}