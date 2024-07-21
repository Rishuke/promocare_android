package com.esgi.promocare_android.network.inscription_connexion

import com.esgi.promocare_android.models.inscription.SubscribeCompanyRequest
import com.esgi.promocare_android.models.inscription.SubscribeCompanyResponse
import com.esgi.promocare_android.models.inscription.SubscribeRequest
import com.esgi.promocare_android.models.inscription.SubscribeResponse
import com.esgi.promocare_android.models.login.LoginRequest
import com.esgi.promocare_android.models.login.LoginResponse
import retrofit2.Call

class InscriptionConnexionRepository(private val inscriptionConnexionServices: InscriptionConnexionServices) {
    fun loginUser(loginRequest: LoginRequest): Call<LoginResponse> {
        return this.inscriptionConnexionServices.loginUser(loginRequest)
    }

    fun loginCompany(loginRequest: LoginRequest): Call<LoginResponse> {
        return this.inscriptionConnexionServices.loginCompany(loginRequest)
    }

    fun registerUser(subscribeRequest: SubscribeRequest): Call<SubscribeResponse> {
        return this.inscriptionConnexionServices.registerUser(subscribeRequest)
    }

    fun registerCompany(subscribeCompanyRequest:  SubscribeCompanyRequest): Call<SubscribeCompanyResponse> {
        return this.inscriptionConnexionServices.registerCompany(subscribeCompanyRequest)
    }
}