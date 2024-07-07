package com.esgi.promocare_android.network.inscriptionConnexion

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
}