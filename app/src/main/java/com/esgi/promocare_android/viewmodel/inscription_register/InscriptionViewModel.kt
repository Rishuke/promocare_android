package com.esgi.promocare_android.viewmodel.inscription_register

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.inscription.SubscribeRequest
import com.esgi.promocare_android.models.inscription.SubscribeResponse
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionRepository
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionViewModel(private val inscriptionConnexionRepository: InscriptionConnexionRepository) {
    fun sendRegisterRequest(subscribeRequest: SubscribeRequest, error: TextView, context: Context) {
        val apiResponse: Call<SubscribeResponse> = inscriptionConnexionRepository.registerUser(subscribeRequest)
        val nextScreen = Intent(context, AnnonceUserActivity::class.java)

        apiResponse.enqueue(object : Callback<SubscribeResponse> {
            override fun onFailure(call: Call<SubscribeResponse>, t: Throwable) {
                Log.e("InscriptionViewModel", "onFailure: ${t.message}", t)
                error.setText(R.string.user_registration_no_connection_error)
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(call: Call<SubscribeResponse>, response: Response<SubscribeResponse>) {
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()?.string()
                    Log.e("InscriptionViewModel", "onResponse: ${response.code()} $errorBody")
                    error.setText(R.string.user_registration_error)
                    error.visibility = TextView.VISIBLE
                    return
                }

                response.body()?.let {
                    Log.d("InscriptionViewModel", "onResponse: ${it.token}")
                    Credential.token = "Bearer ${it.token}"
                    startActivity(context, nextScreen, null)
                } ?: run {
                    Log.e("InscriptionViewModel", "onResponse: response body is null")
                    error.setText(R.string.user_registration_error)
                    error.visibility = TextView.VISIBLE
                }
            }
        })
    }
}
