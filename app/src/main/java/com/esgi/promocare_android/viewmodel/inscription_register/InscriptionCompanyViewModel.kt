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
import com.esgi.promocare_android.views.company_annonce.CompanyAnnonceActivity
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionCompanyViewModel(private val inscriptionConnexionRepository: InscriptionConnexionRepository) {
    fun sendRegisterCompanyRequest(subscribeCompanyRequest: SubscribeCompanyRequest, error: TextView, context: Context) {
        val apiResponse: Call<SubscribeCompanyResponse> = inscriptionConnexionRepository.registerCompany(subscribeCompanyRequest)
        val nextScreen = Intent(context, CompanyAnnonceActivity::class.java)

        apiResponse.enqueue(object : Callback<SubscribeCompanyResponse> {
            override fun onFailure(call: Call<SubscribeCompanyResponse>, t: Throwable) {
                Log.e("InscriptionCompanyViewModel", "onFailure: ${t.message}", t)
                error.setText(R.string.user_registration_no_connection_error)
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(call: Call<SubscribeCompanyResponse>, response: Response<SubscribeCompanyResponse>) {
                if (response.code() == 400) {
                    val errorBody = response.errorBody()?.string()
                    Log.e("InscriptionCompanyViewModel", "onResponse: ${response.code()} $errorBody")
                    error.setText(R.string.user_registration_error)
                    error.visibility = TextView.VISIBLE
                    return
                }

                response.body()?.let {
                    Log.d("InscriptionCompanyViewModel", "onResponse: ${it.token}")
                    Credential.token = "Bearer ${it.token}"
                    startActivity(context, nextScreen, null)
                } ?: run {
                    Log.e("InscriptionCompanyViewModel", "onResponse: response body is null")
                    error.setText(R.string.user_registration_error)
                    error.visibility = TextView.VISIBLE
                }
            }
        })
    }
}
