package com.esgi.promocare_android.viewmodel.annonce

import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.annonce.ReturnAllAnnonceDto
import com.esgi.promocare_android.network.annonce_services.AnnonceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnnonceUserViewModel(private val annonceRepository: AnnonceRepository) {
    var annonceList: MutableLiveData<MutableList<AnnonceModel>> = MutableLiveData()

    fun getAllAnnonce(token:String, loader: ProgressBar, error: TextView){
        val apiResponse = annonceRepository.getAllAnnonce(token)
        loader.visibility = ProgressBar.VISIBLE

        apiResponse.enqueue(object : Callback<ReturnAllAnnonceDto> {
            override fun onFailure(p0: Call<ReturnAllAnnonceDto>, t: Throwable) {
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<ReturnAllAnnonceDto>, response: Response<ReturnAllAnnonceDto>) {
                val responseBody: List<AnnonceDto> = response.body()?.items ?: listOf()
                val mappedResponse = responseBody.map {
                    AnnonceModel(
                        it.uuid,
                        it.companyId,
                        it.location,
                        it.price,
                        it.promo,
                        it.status,
                        it.title,
                        it.description,
                        it.type,
                        it.viewTime,
                        it.updatedAt,
                        it.createdAt
                    )
                }
                annonceList.value = ArrayList(mappedResponse)
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.GONE
            }
        })
    }
}