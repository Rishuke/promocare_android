package com.esgi.promocare_android.network.inscriptionConnexion

import com.esgi.promocare_android.models.login.LoginRequest
import retrofit2.Call

class InscriptionConnexionRepository(private val inscriptionConnexionServices: InscriptionConnexionServices) {
    fun loginUser(loginRequest: LoginRequest): Call<Any> {
        return this.inscriptionConnexionServices.loginUser(loginRequest)
    }
}