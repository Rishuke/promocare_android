package com.esgi.promocare_android.viewmodel.annonce

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.esgi.promocare_android.models.annonce.AnnonceDto
import com.esgi.promocare_android.models.annonce.AnnonceModel
import com.esgi.promocare_android.models.annonce.CreateAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnAnnonceDto
import com.esgi.promocare_android.models.annonce.ReturnCreateAnnonceDto
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.network.annonce_services.AnnonceRepository
import com.esgi.promocare_android.views.company_annonce.CompanyAnnonceActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class CreateAnnonceViewModel(private val annonceRepository: AnnonceRepository) {
    var title:String = ""
    var description:String = ""
    var price:Float = -1f
    var category:String = ""
    var location:String = "No location provided"
    var promo:Int = -1

    fun postAnnonceCompany(context: Context){
        val nextScreen = Intent(context, CompanyAnnonceActivity::class.java)
        val createAnnonceDto = CreateAnnonceDto(this.title,this.description,this.price,this.category,this.location,this.promo)
        val apiResponse = annonceRepository.postAnnonceCompany(Credential.token,createAnnonceDto)

        apiResponse.enqueue(object : Callback<ReturnCreateAnnonceDto> {
            override fun onFailure(p0: Call<ReturnCreateAnnonceDto>, t: Throwable) {
                Log.d("ERROR CREATE", "onFailure: $t")
            }

            override fun onResponse(p0: Call<ReturnCreateAnnonceDto>, response: Response<ReturnCreateAnnonceDto>) {
                startActivity(context,nextScreen,null)
            }
        })
    }
}