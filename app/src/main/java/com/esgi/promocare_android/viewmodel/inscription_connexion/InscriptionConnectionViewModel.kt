package com.esgi.promocare_android.viewmodel.inscription_connexion

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.login.LoginRequest
import com.esgi.promocare_android.models.login.LoginResponse
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.network.inscription_connexion.InscriptionConnexionRepository
import com.esgi.promocare_android.views.conversations.company.LatestConvCompanyActivity
import com.esgi.promocare_android.views.user_annonce.AnnonceUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionConnectionViewModel(private val inscriptionConnexionRepository: InscriptionConnexionRepository){
    fun sendLoginRequest(loginRequest: LoginRequest,error:TextView,context:Context){
        val apiResponse: Call<LoginResponse> = inscriptionConnexionRepository.loginUser(loginRequest)
        val nextScreen = Intent(context, AnnonceUserActivity::class.java)

        apiResponse.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(p0: Call<LoginResponse>, t: Throwable) {
                error.setText(R.string.user_connection_no_connection_error)
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 401) {
                    error.setText(R.string.user_connection_wrong_credentials_error)
                    error.visibility = TextView.VISIBLE
                    return
                }
                Credential.token = "Bearer " +response.body()?.token.toString()
                startActivity(context,nextScreen,null)
            }
        })
    }

    fun sendCompanyLoginRequest(loginRequest: LoginRequest,error:TextView,context: Context){
        val apiResponse: Call<LoginResponse> = inscriptionConnexionRepository.loginCompany(loginRequest)
        val nextScreen = Intent(context, LatestConvCompanyActivity::class.java)


        apiResponse.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(p0: Call<LoginResponse>, t: Throwable) {
                error.setText(R.string.user_connection_no_connection_error)
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 401) {
                    error.setText(R.string.user_connection_wrong_credentials_error)
                    error.visibility = TextView.VISIBLE
                    return
                }
                Credential.token = "Bearer " +response.body()?.token.toString()
                startActivity(context,nextScreen,null)
            }
        })
    }
}