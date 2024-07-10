package com.esgi.promocare_android.network.inscription_connexion

import com.esgi.promocare_android.models.login.LoginRequest
import com.esgi.promocare_android.models.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InscriptionConnexionServices {
    @POST("loginUser")
    fun loginUser(@Body loginRequest : LoginRequest): Call<LoginResponse>

    @POST("loginCompany")
    fun loginCompany(@Body loginRequest : LoginRequest): Call<LoginResponse>
}
