package com.esgi.promocare_android.network.inscription_connexion

import com.esgi.promocare_android.models.inscription.SubscribeCompanyRequest
import com.esgi.promocare_android.models.inscription.SubscribeCompanyResponse
import com.esgi.promocare_android.models.inscription.SubscribeRequest
import com.esgi.promocare_android.models.inscription.SubscribeResponse
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

    @POST("registerUser")
    fun registerUser(@Body subscribeRequest : SubscribeRequest): Call<SubscribeResponse>

    @POST("registerCompany")
    fun registerCompany(@Body subscribeCompanyRequest : SubscribeCompanyRequest): Call<SubscribeCompanyResponse>


}
