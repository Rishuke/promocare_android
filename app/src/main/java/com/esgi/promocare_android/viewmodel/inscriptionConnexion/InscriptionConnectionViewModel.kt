package com.esgi.promocare_android.viewmodel.inscriptionConnexion

import android.widget.TextView
import com.esgi.promocare_android.R
import com.esgi.promocare_android.models.login.LoginRequest
import com.esgi.promocare_android.models.login.LoginResponse
import com.esgi.promocare_android.network.Credential
import com.esgi.promocare_android.network.inscriptionConnexion.InscriptionConnexionRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionConnectionViewModel(private val inscriptionConnexionRepository: InscriptionConnexionRepository){
    fun sendLoginRequest(loginRequest: LoginRequest,error:TextView){
        val apiResponse: Call<LoginResponse> = inscriptionConnexionRepository.loginUser(loginRequest)

        apiResponse.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(p0: Call<LoginResponse>, t: Throwable) {
                error.setText(R.string.user_connection_no_connection_error)
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 401) {
                    error.setText(R.string.user_connection_wrong_credentials_error)
                    error.visibility = TextView.VISIBLE
                }
                Credential.token = response.body()?.token.toString()
            }
        })
    }

    fun sendCompanyLoginRequest(loginRequest: LoginRequest,error:TextView){
        val apiResponse: Call<LoginResponse> = inscriptionConnexionRepository.loginCompany(loginRequest)

        apiResponse.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(p0: Call<LoginResponse>, t: Throwable) {
                error.setText(R.string.user_connection_no_connection_error)
                error.visibility = TextView.VISIBLE
            }

            override fun onResponse(p0: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 401) {
                    error.setText(R.string.user_connection_wrong_credentials_error)
                    error.visibility = TextView.VISIBLE
                }
                Credential.token = response.body()?.token.toString()
            }
        })
    }
}