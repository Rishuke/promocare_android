package com.esgi.promocare_android.viewmodel.inscription_register

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.inscription.SubscribeCompanyRequest
import com.esgi.promocare_android.models.inscription.SubscribeCompanyResponse
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionRepository
import com.esgi.promocare_android.viewmodel.annonce.AnnonceCompanyViewModel
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionCompanyViewModel(private val inscriptionConnexionRepository: InscriptionConnexionRepository) {
    fun sendRegisterCompanyRequest(subscribeCompanyRequest: SubscribeCompanyRequest, error: TextView, context: Context) {
        val apiResponse: Call<SubscribeCompanyResponse> = inscriptionConnexionRepository.registerCompany(subscribeCompanyRequest)
        val nextScreen = Intent(context, AnnonceCompanyViewModel::class.java)

        apiResponse.enqueue(object : Callback<SubscribeCompanyResponse> {
            override fun onFailure(call: Call<SubscribeCompanyResponse>, t: Throwable) {
                error.setText(R.string.user_registration_no_connection_error)
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(call: Call<SubscribeCompanyResponse>, response: Response<SubscribeCompanyResponse>) {
                if (response.code() == 400) {
                    val errorBody = response.errorBody()?.string()
                    error.setText(R.string.user_registration_error)
                    error.visibility = TextView.VISIBLE
                    return
                }

                response.body()?.let {
                    Credential.token = "Bearer ${it.token}"
                    startActivity(context, nextScreen, null)
                } ?: run {
                    error.setText(R.string.user_registration_error)
                    error.visibility = TextView.VISIBLE
                }
            }
        })
    }
}
