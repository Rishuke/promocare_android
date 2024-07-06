package com.esgi.promocare_android.network.inscriptionConnexion

import com.esgi.promocare_android.models.login.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface InscriptionConnexionServices {
    @POST("loginUser")
    fun loginUser(@Body loginRequest : LoginRequest): Call<Any>
}
