package com.esgi.promocare_android.network

import com.esgi.promocare_android.model.LoginRequest
import com.esgi.promocare_android.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {
    @GET("login")
    fun login(@Body loginRequest: LoginRequest?): Call<LoginResponse?>?
}