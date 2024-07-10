package com.esgi.promocare_android.viewmodel.annonce

import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import com.esgi.promocare_android.network.annonce_services.AnnonceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AnnonceCompanyViewModel(private val annonceRepository: AnnonceRepository) {

    var annonceList: MutableLiveData<MutableList<AnnonceModel>> = MutableLiveData()

    fun getAnnonceCompany(token:String,loader:ProgressBar,error:TextView){
        val apiResponse = annonceRepository.getAnnoncesCompany(token)
        loader.visibility = ProgressBar.VISIBLE

        apiResponse.enqueue(object : Callback<ReturnAnnonceDto> {
            override fun onFailure(p0: Call<ReturnAnnonceDto>, t: Throwable) {
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<ReturnAnnonceDto>, response: Response<ReturnAnnonceDto>) {
                val responseBody: List<AnnonceDto> = response.body()?.item ?: listOf()
                val mappedResponse = responseBody.map {
                    AnnonceModel(
                        it.uuid,
                        it.company_id,
                        it.location,
                        it.price,
                        it.promo,
                        it.status,
                        it.title,
                        it.type,
                        it.view_time,
                        it.updated_at,
                        it.created_at
                    )
                }
                annonceList.value = ArrayList(mappedResponse)
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.GONE
            }
        })
    }
}