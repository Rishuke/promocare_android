package com.esgi.promocare_android.viewmodel.annonce
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnCreateAnnonceDto
import com.esgi.promocare_android.models.annonce.CreateAnnonceDto
import com.esgi.promocare_android.network.annonce_services.AnnonceRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnnonceCompanyViewModel(private val annonceRepository: AnnonceRepository) {

    var annonceList: MutableLiveData<MutableList<AnnonceModel>> = MutableLiveData()

    fun getAnnonceCompany(token: String, loader: ProgressBar, error: TextView) {
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
                        it.description,
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

    fun deleteAnnonceCompany(token: String, annonceId: String, loader: ProgressBar, error: TextView) {
        val apiResponse = annonceRepository.deleteAnnonceCompany(token, annonceId)
        loader.visibility = ProgressBar.VISIBLE

        apiResponse.enqueue(object : Callback<ReturnCreateAnnonceDto> {
            override fun onFailure(p0: Call<ReturnCreateAnnonceDto>, t: Throwable) {
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<ReturnCreateAnnonceDto>, response: Response<ReturnCreateAnnonceDto>) {
                loader.visibility = ProgressBar.GONE
                if (response.isSuccessful) {
                    annonceList.value = annonceList.value?.filter { it.uuid != annonceId }?.toMutableList()
                } else {
                    error.visibility = TextView.VISIBLE
                }
            }
        })
    }

    fun updateAnnonceCompany(token: String, annonceId: String, createAnnonceDto: CreateAnnonceDto, loader: ProgressBar, error: TextView, onSuccess: () -> Unit) {
        val apiResponse = annonceRepository.updateAnnonceCompany(token, annonceId, createAnnonceDto)
        loader.visibility = ProgressBar.VISIBLE

        apiResponse.enqueue(object : Callback<ReturnCreateAnnonceDto> {
            override fun onFailure(p0: Call<ReturnCreateAnnonceDto>, t: Throwable) {
                loader.visibility = ProgressBar.GONE
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<ReturnCreateAnnonceDto>, response: Response<ReturnCreateAnnonceDto>) {
                loader.visibility = ProgressBar.GONE
                if (response.isSuccessful) {
                    // Mise à jour de la liste locale avec la nouvelle annonce
                    annonceList.value = annonceList.value?.map {
                        if (it.uuid == annonceId) {
                            it.copy(
                                title = createAnnonceDto.title,
                                description = createAnnonceDto.description,
                                price = createAnnonceDto.price,
                                type = createAnnonceDto.type,
                                location = createAnnonceDto.location,
                                promo = createAnnonceDto.promo
                            )
                        } else {
                            it
                        }
                    }?.toMutableList()
                    onSuccess()
                } else {
                    error.visibility = TextView.VISIBLE
                }
            }
        })
    }
}
